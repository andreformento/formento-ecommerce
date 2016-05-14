package com.formento.ecommerce.ecommerceOrder.service;

import com.formento.ecommerce.ecommerceOrder.model.EcommerceOrder;

public interface EcommerceOrderService {

    EcommerceOrder create(EcommerceOrder ecommerceOrder);

    EcommerceOrder changeStatusOrder(Long orderId, EcommerceOrder ecommerceOrderToChange);
}
