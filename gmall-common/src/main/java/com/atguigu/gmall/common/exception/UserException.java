package com.atguigu.gmall.common.exception;

/**
 * @author Hasee  2021/3/27 13:29 周六
 * @version JDK 8.017
 * @description:
 */
public class UserException extends RuntimeException {

    public UserException() {
        super();
    }

    public UserException(String message) {
        super(message);
    }
}
