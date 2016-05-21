package com.formento.ecommerce.product.service;

import com.formento.ecommerce.product.model.Product;
import com.formento.ecommerce.product.repository.ProductRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@NoArgsConstructor
@Service
public class ProductServiceDefault implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Iterable<Product> findByAvaliable() {
        return productRepository.findByAvaliable();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return Optional.ofNullable(productRepository.findOne(id));
    }

}
