package com.atguigu.gmall.common.exception;

/**
 * @author Hasee  2021/3/23 21:45 周二
 * @version JDK 8.017
 * @description:
 */
public class ItemException extends RuntimeException {

    public ItemException() {
        super();
    }

    public ItemException(String message) {
        super(message);
    }
}
