package org.lay.product.service.impl;

import org.lay.product.common.DecreaseStockInput;
import org.lay.product.common.ProductInfoOutput;
import org.lay.product.dataobject.ProductInfo;
import org.lay.product.enums.ProductStatusEnum;
import org.lay.product.enums.ResultEnum;
import org.lay.product.exception.ProductException;
import org.lay.product.repository.ProductInfoRepository;
import org.lay.product.service.ProductInfoService;
import org.lay.product.utils.JsonUtil;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 商品Service
 * Create by Lay
 * 2018-03-14 13:33
 */
@Service
public class ProductInfoServiceImpl implements ProductInfoService {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Override
    public List<ProductInfo> findUpAll() {
        List<ProductInfo> productInfoList = productInfoRepository
                .findByProductStatus(ProductStatusEnum.UP.getCode());
        return productInfoList;
    }

    @Override
    public List<ProductInfoOutput> findProductInfoList(List<String> productIdList) {
        return productInfoRepository.findByProductIdIn(productIdList).stream()
                .map(e -> {
                    ProductInfoOutput productInfoOutput = new ProductInfoOutput();
                    BeanUtils.copyProperties(e, productInfoOutput);
                    return productInfoOutput;
                }).collect(Collectors.toList());
    }

    /**
     * 调用扣库存&发送消息
     * @param decreaseStockInputList
     */
    @Override
    public void decreaseStock(List<DecreaseStockInput> decreaseStockInputList) {
        List<ProductInfo> productInfoList = decreaseStockProcess(decreaseStockInputList);

        List<ProductInfoOutput> productInfoOutputList = productInfoList.stream().map(e -> {
            // 库存扣完发送mq消息
            ProductInfoOutput productInfoOutput = new ProductInfoOutput();
            BeanUtils.copyProperties(e, productInfoOutput);
            return productInfoOutput;
        }).collect(Collectors.toList());
        // mq发送消息列表
        amqpTemplate.convertAndSend("productInfo", JsonUtil.toJson(productInfoOutputList));
    }

    /**
     * 扣库存
     * @param decreaseStockInputList
     * @return productInfoList
     */
    @Transactional
    public List<ProductInfo> decreaseStockProcess(List<DecreaseStockInput> decreaseStockInputList) {
        List<ProductInfo> productInfoList = new ArrayList<>();
        for (DecreaseStockInput cartDTO : decreaseStockInputList) {
            Optional<ProductInfo> productInfoOptional = productInfoRepository.findById(
                    cartDTO.getProductId());
            // 判断商品是否存在
            if (!productInfoOptional.isPresent()) {
                throw new ProductException(ResultEnum.PRODUCT_NOT_EXISTS);
            }
            // 调用get方法获取当前对象
            ProductInfo productInfo = productInfoOptional.get();
            // 库存处理
            Integer result = productInfo.getProductStock() - cartDTO.getProductQuantity();
            // 库存异常
            if (result < 0) {
                throw new ProductException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            // 设置新的库存
            productInfo.setProductStock(result);
            productInfoRepository.save(productInfo);

            productInfoList.add(productInfo);
        }
        return productInfoList;
    }

}
