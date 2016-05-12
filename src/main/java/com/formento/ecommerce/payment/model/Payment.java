package com.formento.ecommerce.payment.model;

public interface Payment {

    MethodPayment getMethodPayment();

    FundingInstrument getFundingInstrument();

}
