package org.lay.product.dataobject;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * Create by Lay
 * 2018-03-14 13:21
 */
@Data
@Entity
public class ProductCategory {

    @Id
    @GeneratedValue
    private Integer categoryId;

    /** 类目名称 */
    private String categoryName;

    /** 类目编号 */
    private Integer categoryType;

    /** 创建时间 */
    private Date createTime;

    /** 更新时间 */
    private Date updateTime;

}
