package org.lay.product.enums;

import lombok.Getter;

/**
 * Create by Lay
 * 2018-03-15 23:14
 */
@Getter
public enum ResultEnum {

    PRODUCT_NOT_EXISTS(1, "商品不存在"),

    PRODUCT_STOCK_ERROR(2, "商品库存异常"),
    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
