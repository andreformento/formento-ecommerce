package com.formento.ecommerce.exception;

public class EcommerceExceptionDefault extends RuntimeException implements EcommerceException {

    public EcommerceExceptionDefault(String s) {
        super(s);
    }

    @Override
    public EcommerceExceptionMessage buildEcommerceExceptionMessage() {
        return new EcommerceExceptionMessageDefault(getMessage());
    }

}
