package com.formento.ecommerce.productPrice.repository;

import com.formento.ecommerce.product.model.Product;
import com.formento.ecommerce.productPrice.model.ProductPriceDefault;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.data.rest.webmvc.RepositoryRestController;

import java.util.Collection;

@RepositoryRestController
public interface ProductPriceRepository extends PagingAndSortingRepository<ProductPriceDefault, Long> {

    @RestResource(rel = "byProduct", path = "byProduct")
    Collection<ProductPriceDefault> findByProduct(@Param("product") Product product);

}
