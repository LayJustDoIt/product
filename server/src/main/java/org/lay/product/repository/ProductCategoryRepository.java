package org.lay.product.repository;

import org.lay.product.dataobject.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * Create by Lay
 * 2018-03-14 13:27
 */
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, String> {

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
