package com.formento.ecommerce.ecommerceOrder.service;

import com.formento.ecommerce.ecommerceOrder.model.EcommerceOrder;
import com.formento.ecommerce.ecommerceOrder.model.StatusEcommerceOrder;

public interface EcommerceOrderService {

    EcommerceOrder create(EcommerceOrder ecommerceOrder);

    EcommerceOrder changeStatusOrder(EcommerceOrder ecommerceOrder, String integrationId, StatusEcommerceOrder newStatus);
}
