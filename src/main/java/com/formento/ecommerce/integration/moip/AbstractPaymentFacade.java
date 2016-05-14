package com.formento.ecommerce.integration.moip;

import br.com.moip.request.FundingInstrumentRequest;
import br.com.moip.request.PaymentRequest;
import com.formento.ecommerce.ecommerceOrder.model.EcommerceOrder;
import com.formento.ecommerce.exception.BusinessEcommerceException;
import com.formento.ecommerce.integration.PaymentFacade;
import com.formento.ecommerce.payment.model.FundingInstrument;
import com.formento.ecommerce.payment.model.MethodPayment;
import com.formento.ecommerce.payment.model.Payment;
import com.formento.ecommerce.payment.model.PaymentDefault;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class AbstractPaymentFacade implements PaymentFacade {

    private final MoipApi moipApi;
    private final EcommerceOrder ecommerceOrder;
    private final MethodPayment methodPayment;
    private final FundingInstrument fundingInstrument;

    @Override
    public Payment makePayment() {
        FundingInstrumentRequest fundingInstrumentRequest = createFundingInstrumentRequest();

        PaymentRequest paymentRequest = new PaymentRequest()
                .orderId(ecommerceOrder
                        .getIntegrationId()
                        .orElseThrow(() -> new BusinessEcommerceException("payment.order.integrationIdNotFound")))
                .installmentCount(methodPayment.getCount())
                .fundingInstrument(fundingInstrumentRequest);

        moipApi.getApi().payment().create(paymentRequest);

        return new PaymentDefault
                .Builder()
                .withFundingInstrument(fundingInstrument)
                .withMethodPayment(methodPayment)
                .withEcommerceOrder(ecommerceOrder)
                .build();
    }

    protected abstract FundingInstrumentRequest createFundingInstrumentRequest();

}
