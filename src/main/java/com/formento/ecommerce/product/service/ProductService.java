package com.formento.ecommerce.product.service;

import com.formento.ecommerce.product.model.Product;

public interface ProductService {

    Iterable<Product> findByAvaliable();

    Product findById(Long id);

}
