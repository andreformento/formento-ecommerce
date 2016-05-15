package com.formento.ecommerce.payment.service;

import com.formento.ecommerce.ecommerceOrder.service.EcommerceOrderService;
import com.formento.ecommerce.installment.factory.MethodPaymentFactoryDefault;
import com.formento.ecommerce.integration.moip.CreditCardPaymentFacade;
import com.formento.ecommerce.integration.moip.MoipApi;
import com.formento.ecommerce.integration.moip.MoipConfiguration;
import com.formento.ecommerce.payment.model.Payment;
import com.formento.ecommerce.payment.model.creditCard.CreditCardEcommerceFundingInstrument;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Service
public class CreditCardPaymentService implements PaymentService<CreditCardEcommerceFundingInstrument> {

    @Autowired
    private MoipApi moipApi;

    @Autowired
    private MoipConfiguration moipConfiguration;

    @Autowired
    private EcommerceOrderService ecommerceOrderService;

    @Override
    public Payment createPayment(BigDecimal totalValue, Integer quantity, CreditCardEcommerceFundingInstrument fundingInstrument) {
        return new CreditCardPaymentFacade(
                moipApi,
                moipConfiguration,
                ecommerceOrderService.getValidatedCurrentOrder(),
                new MethodPaymentFactoryDefault().makeMethodPayment(totalValue, quantity),
                fundingInstrument).makePayment();
    }

}
