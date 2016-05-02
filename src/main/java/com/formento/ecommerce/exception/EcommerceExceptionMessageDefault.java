package com.formento.ecommerce.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public class EcommerceExceptionMessageDefault implements EcommerceExceptionMessage, Serializable {

    private final String message;

}
