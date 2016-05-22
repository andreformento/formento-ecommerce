package com.formento.ecommerce.payment.service.request;

import com.formento.ecommerce.payment.model.FundingInstrument;

public interface PaymentRequest {

    Integer getInstallmentCount();

    FundingInstrument generateFundingInstrument();

}
