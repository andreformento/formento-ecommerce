package com.formento.ecommerce.payment.model;

import com.formento.ecommerce.ecommerceOrder.model.EcommerceOrder;

import java.util.Optional;

public interface Payment {

    MethodPayment getMethodPayment();

    FundingInstrument getFundingInstrument();

    Optional<EcommerceOrder> getEcommerceOrder();

}
