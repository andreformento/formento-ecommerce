package com.formento.ecommerce.productPrice.repository;

import com.formento.ecommerce.product.model.Product;
import com.formento.ecommerce.productPrice.model.ProductPriceEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.data.rest.webmvc.RepositoryRestController;

import java.util.Collection;

@RepositoryRestController
public interface ProductPriceRepository extends PagingAndSortingRepository<ProductPriceEntity, Long> {

    @RestResource(rel = "byProduct", path = "byProduct")
    Collection<ProductPriceEntity> findByProduct(@Param("product") Product product);

}
