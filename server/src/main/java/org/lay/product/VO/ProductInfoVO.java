package org.lay.product.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Create by Lay
 * 2017-12-31 12:50
 */
@Data
public class ProductInfoVO implements Serializable {

    private static final long serialVersionUID = 8895962786146594984L;

    @JsonProperty(value = "id")
    private String productId;

    @JsonProperty(value = "name")
    private String productName;

    @JsonProperty(value = "price")
    private BigDecimal productPrice;

    @JsonProperty(value = "description")
    private String productDescription;

    @JsonProperty(value = "icon")
    private String productIcon;
}
