package com.formento.ecommerce.product.repository;

import com.formento.ecommerce.product.model.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;

@RepositoryRestController
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {

    @Query(" select product " +
            " from Product product " +
            " where product.availability > 0")
    Iterable<Product> findByAvaliable();

}
