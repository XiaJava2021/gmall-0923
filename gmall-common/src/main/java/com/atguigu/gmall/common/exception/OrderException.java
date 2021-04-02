package com.atguigu.gmall.common.exception;

/**
 * @author Hasee  2021/3/31 11:16 周三
 * @version JDK 8.017
 * @description:
 */
public class OrderException extends RuntimeException{
    public OrderException() {
        super();
    }

    public OrderException(String message) {
        super(message);
    }
}
