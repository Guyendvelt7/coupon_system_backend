package com.jb.coupon2demo.exceptions;

public class CustomExceptions extends Exception{
    public CustomExceptions(OptionalExceptionMessages message) {
        super(message.getMessage());
    }
}
