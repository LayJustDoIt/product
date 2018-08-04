package org.lay.product.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Create by Lay
 * 2017-12-31 12:42
 */
@Data
public class ProductVO implements Serializable {

    private static final long serialVersionUID = -846759200757178945L;

    @JsonProperty(value = "name")
    private String categoryName;

    @JsonProperty(value = "type")
    private Integer categoryType;

    @JsonProperty(value = "foods")
    private List<ProductInfoVO> productInfoVOList;
}
