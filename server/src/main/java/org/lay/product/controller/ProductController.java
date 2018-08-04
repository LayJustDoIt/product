package org.lay.product.controller;

import org.lay.product.VO.ProductInfoVO;
import org.lay.product.VO.ProductVO;
import org.lay.product.VO.ResultVO;
import org.lay.product.common.DecreaseStockInput;
import org.lay.product.common.ProductInfoOutput;
import org.lay.product.dataobject.ProductCategory;
import org.lay.product.dataobject.ProductInfo;
import org.lay.product.service.ProductCategoryService;
import org.lay.product.service.ProductInfoService;
import org.lay.product.utils.ResultVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Create by Lay
 * 2018-03-14 13:30
 */
@RestController
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private ProductCategoryService productCategoryService;

    /**
     * 商品列表
     */
    @GetMapping("/list")
    public ResultVO productList() {
        // 查询在架商品
        List<ProductInfo> productInfoList = productInfoService.findUpAll();
        // 所有categoryType
        List<Integer> categoryTypeList = productInfoList.stream()
                .map(ProductInfo::getCategoryType).collect(Collectors.toList());
        // 查询所有商品类目
        List<ProductCategory> productCategoryList = productCategoryService.findByCategoryTypeIn(categoryTypeList);
        List<ProductVO> productVOList = new ArrayList<>();
        for (ProductCategory productCategory : productCategoryList) {
            ProductVO productVO = new ProductVO();
            productVO.setCategoryName(productCategory.getCategoryName());
            productVO.setCategoryType(productCategory.getCategoryType());
            // 遍历商品集合
            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for (ProductInfo productInfo : productInfoList) {
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo, productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }

        return ResultVOUtil.success(productVOList);
    }

    /**
     * 商品信息
     * @param productIdList
     * @return
     */
    @PostMapping("/listForOrder")
    public List<ProductInfoOutput> listForOrder(@RequestBody List<String> productIdList) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<ProductInfoOutput> productInfoList = productInfoService.findProductInfoList(productIdList);
        return productInfoList;
    }

    /**
     * 扣库存
     * @param cartDTOList
     */
    @PostMapping("/decreaseStock")
    public void decreaseStock(@RequestBody List<DecreaseStockInput> cartDTOList) {
        productInfoService.decreaseStock(cartDTOList);
    }

}
