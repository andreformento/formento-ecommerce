package com.formento.ecommerce.payment.model;

import com.formento.ecommerce.installment.model.PaymentInstallment;

public interface Payment {

    PaymentInstallment getPaymentInstallment();

    FundingInstrument getFundingInstrument();

}
