package com.formento.ecommerce.payment.service;

import com.formento.ecommerce.ecommerceOrder.model.EcommerceOrder;
import com.formento.ecommerce.ecommerceOrder.service.EcommerceOrderService;
import com.formento.ecommerce.installment.factory.MethodPaymentFactoryDefault;
import com.formento.ecommerce.integration.moip.BoletoPaymentFacade;
import com.formento.ecommerce.integration.moip.MoipApi;
import com.formento.ecommerce.payment.model.MethodPayment;
import com.formento.ecommerce.payment.model.Payment;
import com.formento.ecommerce.payment.service.request.BoletoPaymentRequest;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@NoArgsConstructor
@Service
public class BoletoPaymentService implements PaymentService<BoletoPaymentRequest> {

    @Autowired
    private MoipApi moipApi;

    @Autowired
    private EcommerceOrderService ecommerceOrderService;

    @Autowired
    private PaymentEditService paymentEditService;

    @Override
    public Payment createPayment(Long orderId, BoletoPaymentRequest boletoPaymentRequest) {
        EcommerceOrder order = ecommerceOrderService.getValidatedOrderById(orderId);

        MethodPayment methodPayment = new MethodPaymentFactoryDefault().makeMethodPayment(order.getTotalValue(), boletoPaymentRequest.getInstallmentCount());

        return paymentEditService.create(
                new BoletoPaymentFacade(
                        moipApi,
                        order,
                        methodPayment,
                        boletoPaymentRequest.generateFundingInstrument()
                ).makePayment(),
                methodPayment.getTotalValue()
        );
    }

}
