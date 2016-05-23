package com.formento.ecommerce.installment.factory;

import com.formento.ecommerce.exception.BusinessEcommerceException;
import com.formento.ecommerce.payment.model.FundingInstrumentOption;
import com.formento.ecommerce.payment.model.MethodPayment;
import com.formento.ecommerce.payment.model.MethodPaymentInNTimes;
import com.formento.ecommerce.payment.model.UniqueMethodPayment;

import java.math.BigDecimal;

public class MethodPaymentFactoryDefault implements MethodPaymentFactory {

    @Override
    public MethodPayment makeMethodPayment(BigDecimal totalValue, Integer count) {
        if (count.equals(FundingInstrumentOption.BOLETO.getCountMax())) {
            return new UniqueMethodPayment(totalValue);
        } else if (count > FundingInstrumentOption.BOLETO.getCountMax() && count <= FundingInstrumentOption.CREDIT_CARD.getCountMax()) {
            return new MethodPaymentInNTimes(totalValue.multiply(BigDecimal.valueOf(1.025)), count);
        }

        throw new BusinessEcommerceException("methodPayment.quantity.notAccepted");
    }

}
