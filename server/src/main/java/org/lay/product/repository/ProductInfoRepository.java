package org.lay.product.repository;

import org.lay.product.common.ProductInfoOutput;
import org.lay.product.dataobject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Create by Lay
 * 2018-03-14 13:23
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo, String> {

    /** 所有在架 */
    List<ProductInfo> findByProductStatus(Integer productStatus);

    /** 所有商品列表 */
    List<ProductInfo> findByProductIdIn(List<String> productIdList);
}
