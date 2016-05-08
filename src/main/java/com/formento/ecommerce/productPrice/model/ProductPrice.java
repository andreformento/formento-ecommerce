package com.formento.ecommerce.productPrice.model;

import com.formento.ecommerce.product.model.Product;

import java.math.BigDecimal;

public interface ProductPrice {
    Product getProduct();

    BigDecimal getTotalPrice();
}
