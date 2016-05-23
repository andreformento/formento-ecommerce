package com.formento.ecommerce.payment.repository;

import com.formento.ecommerce.payment.model.PaymentEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends PagingAndSortingRepository<PaymentEntity, Long> {

}
