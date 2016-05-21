package com.formento.ecommerce.product.service;

import com.formento.ecommerce.product.model.Product;

import java.util.Optional;

public interface ProductService {

    Iterable<Product> findByAvaliable();

    Optional<Product> findById(Long id);

}
