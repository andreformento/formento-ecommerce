package com.formento.ecommerce.installment.factory;

import com.formento.ecommerce.exception.BusinessEcommerceException;
import com.formento.ecommerce.installment.model.PaymentInstallment;
import com.formento.ecommerce.installment.model.PaymentInstallmentInNTimes;
import com.formento.ecommerce.installment.model.PaymentInstallmentUnique;
import com.formento.ecommerce.payment.model.FundingInstrumentOption;

import java.math.BigDecimal;

public class PaymentInstallmentFactoryDefault implements PaymentInstallmentFactory {

    @Override
    public PaymentInstallment makePaymentInstallment(BigDecimal totalValue, Integer count) {
        if (count.equals(FundingInstrumentOption.BOLETO.getCountMax())) {
            return new PaymentInstallmentUnique(totalValue);
        } else if (count > FundingInstrumentOption.BOLETO.getCountMax() && count <= FundingInstrumentOption.CREDIT_CARD.getCountMax()) {
            return new PaymentInstallmentInNTimes(totalValue, count);
        }

        throw new BusinessEcommerceException("paymentInstallment.quantity.notAccepted");
    }

}
