package com.formento.ecommerce.ecommerceOrder.service;

import com.formento.ecommerce.ecommerceOrder.model.EcommerceOrder;
import com.formento.ecommerce.ecommerceOrder.model.StatusEcommerceOrder;
import com.formento.ecommerce.ecommerceOrder.repository.EcommerceOrderRepository;
import com.formento.ecommerce.exception.BusinessEcommerceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EcommerceOrderServiceDefault implements EcommerceOrderService {

    @Autowired
    private EcommerceOrderRepository ecommerceOrderRepository;

    @Override
    public EcommerceOrder create(EcommerceOrder ecommerceOrder) {
        return ecommerceOrderRepository.save(ecommerceOrder);
    }

    @Override
    public EcommerceOrder changeStatusOrder(EcommerceOrder ecommerceOrder, String integrationId, StatusEcommerceOrder newStatus) {
        return ecommerceOrderRepository.save(
                new EcommerceOrder
                        .Builder()
                        .changeStatus(
                                Optional.ofNullable(ecommerceOrderRepository.findOne(ecommerceOrder.getId()))
                                        .orElseThrow(() -> new BusinessEcommerceException("ecommerceOrder.cannotChangeStatusBecauseDotNotExists")),
                                integrationId,
                                newStatus)
                        .build()
        );
    }

}
