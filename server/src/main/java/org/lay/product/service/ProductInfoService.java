package org.lay.product.service;

import org.lay.product.common.DecreaseStockInput;
import org.lay.product.common.ProductInfoOutput;
import org.lay.product.dataobject.ProductInfo;

import java.util.List;

/**
 * Create by Lay
 * 2018-03-14 13:33
 */
public interface ProductInfoService {

    /**
     * 所有在售商品
     *
     * @return
     */
    List<ProductInfo> findUpAll();

    /**
     * 查询所有商品根据id
     * @param productIdList
     * @return
     */
    List<ProductInfoOutput> findProductInfoList(List<String> productIdList);

    /**
     * 扣库存
     * @param cartDTOList
     */
    void decreaseStock(List<DecreaseStockInput> cartDTOList);
}
