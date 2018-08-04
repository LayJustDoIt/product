package org.lay.product.exception;

import org.lay.product.enums.ResultEnum;

/**
 * Create by Lay
 * 2018-03-15 23:12
 */
public class ProductException extends RuntimeException {

    private Integer code;

    private String message;

    public ProductException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public ProductException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.message = resultEnum.getMessage();
    }
}
