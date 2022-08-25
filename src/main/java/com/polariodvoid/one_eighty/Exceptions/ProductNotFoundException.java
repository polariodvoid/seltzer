package com.polariodvoid.one_eighty.Exceptions;

public class ProductNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;

    public ProductNotFoundException(String message) {
        super(message);
    }
}
// Compare this snippet from ShopmeCommon/src/main/java/com/shopme/common/exception/CustomerNotFoundException.java:
