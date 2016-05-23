package com.formento.ecommerce.payment.service;

import com.formento.ecommerce.ecommerceOrder.model.EcommerceOrder;
import com.formento.ecommerce.ecommerceOrder.service.EcommerceOrderService;
import com.formento.ecommerce.installment.factory.MethodPaymentFactoryDefault;
import com.formento.ecommerce.integration.moip.CreditCardPaymentFacade;
import com.formento.ecommerce.integration.moip.MoipApi;
import com.formento.ecommerce.integration.moip.MoipConfiguration;
import com.formento.ecommerce.payment.model.MethodPayment;
import com.formento.ecommerce.payment.model.Payment;
import com.formento.ecommerce.payment.service.request.CreditCardPaymentRequest;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@NoArgsConstructor
@Service
public class CreditCardPaymentService implements PaymentService<CreditCardPaymentRequest> {

    @Autowired
    private MoipApi moipApi;

    @Autowired
    private MoipConfiguration moipConfiguration;

    @Autowired
    private EcommerceOrderService ecommerceOrderService;

    @Autowired
    private PaymentEditService paymentEditService;

    @Override
    public Payment createPayment(Long orderId, CreditCardPaymentRequest paymentRequest) {
        // TODO "salvar"
        EcommerceOrder order = ecommerceOrderService.getValidatedOrderById(orderId);
        MethodPayment methodPayment = new MethodPaymentFactoryDefault().makeMethodPayment(order.getTotalValue(), paymentRequest.getInstallmentCount());
        return paymentEditService.create(
                new CreditCardPaymentFacade(
                        moipApi,
                        moipConfiguration,
                        order,
                        methodPayment,
                        paymentRequest.generateFundingInstrument()).makePayment(),
                methodPayment.getTotalValue()
        );
    }

}
