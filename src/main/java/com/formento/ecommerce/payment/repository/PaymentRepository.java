package com.formento.ecommerce.payment.repository;

import com.formento.ecommerce.payment.model.PaymentEntity;
import com.formento.ecommerce.product.model.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;

@RepositoryRestController
public interface PaymentRepository extends PagingAndSortingRepository<PaymentEntity, Long> {

}
