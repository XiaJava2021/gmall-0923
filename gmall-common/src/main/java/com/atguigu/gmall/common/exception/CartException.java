package com.atguigu.gmall.common.exception;

/**
 * @author Hasee  2021/3/29 14:15 周一
 * @version JDK 8.017
 * @description:
 */
public class CartException extends RuntimeException{
    public CartException() {
        super();
    }

    public CartException(String message) {
        super(message);
    }
}
