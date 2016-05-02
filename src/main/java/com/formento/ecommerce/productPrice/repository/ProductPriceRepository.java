package com.formento.ecommerce.productPrice.repository;

import com.formento.ecommerce.productPrice.model.ProductPrice;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;

@RepositoryRestController
public interface ProductPriceRepository extends PagingAndSortingRepository<ProductPrice, Long> {

}
