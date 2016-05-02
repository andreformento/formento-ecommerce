package com.formento.ecommerce.product.repository;

import com.formento.ecommerce.product.model.Product;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;

@RepositoryRestController
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {

}
