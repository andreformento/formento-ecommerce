package com.formento.ecommerce.payment.service;

import com.formento.ecommerce.ecommerceOrder.service.EcommerceOrderService;
import com.formento.ecommerce.installment.factory.MethodPaymentFactoryDefault;
import com.formento.ecommerce.integration.moip.BoletoPaymentFacade;
import com.formento.ecommerce.integration.moip.MoipApi;
import com.formento.ecommerce.payment.model.Payment;
import com.formento.ecommerce.payment.model.boleto.BoletoEcommerceFundingInstrument;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Service
public class BoletoPaymentService implements PaymentService<BoletoEcommerceFundingInstrument> {

    @Autowired
    private MoipApi moipApi;

    @Autowired
    private EcommerceOrderService ecommerceOrderService;

    @Override
    public Payment createPayment(BigDecimal totalValue, Integer quantity, BoletoEcommerceFundingInstrument fundingInstrument) {
        return new BoletoPaymentFacade(
                moipApi,
                ecommerceOrderService.getValidatedCurrentOrder(),
                new MethodPaymentFactoryDefault().makeMethodPayment(totalValue, quantity),
                fundingInstrument).makePayment();
    }

}
