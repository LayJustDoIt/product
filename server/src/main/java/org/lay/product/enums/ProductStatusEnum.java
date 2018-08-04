package org.lay.product.enums;

import lombok.Getter;

/**
 * Create by Lay
 * 2018-03-14 13:20
 */
@Getter
public enum ProductStatusEnum {

    UP(0, "在架"),
    DOWN(1, "下架");

    private Integer code;
    private String message;

    ProductStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
