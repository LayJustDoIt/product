package org.lay.product.service;

import org.lay.product.dataobject.ProductCategory;
import java.util.List;

/**
 * Create by Lay
 * 2018-03-14 13:35
 */
public interface ProductCategoryService {

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

}
