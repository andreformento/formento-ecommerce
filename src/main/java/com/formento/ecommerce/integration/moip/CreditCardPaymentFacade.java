package com.formento.ecommerce.integration.moip;

import br.com.moip.request.*;
import com.formento.ecommerce.ecommerceOrder.model.EcommerceOrder;
import com.formento.ecommerce.integration.PaymentFacade;
import com.formento.ecommerce.payment.model.MethodPayment;
import com.formento.ecommerce.payment.model.creditCard.CreditCardEcommerceFundingInstrument;

public class CreditCardPaymentFacade extends AbstractPaymentFacade implements PaymentFacade {

    private final MoipConfiguration moipConfiguration;
    private final CreditCardEcommerceFundingInstrument creditCardEcommerceFundingInstrument;

    public CreditCardPaymentFacade(MoipApi moipApi, MoipConfiguration moipConfiguration, EcommerceOrder ecommerceOrder, MethodPayment methodPayment, CreditCardEcommerceFundingInstrument fundingInstrument) {
        super(moipApi, ecommerceOrder, methodPayment, fundingInstrument);
        this.moipConfiguration = moipConfiguration;
        this.creditCardEcommerceFundingInstrument = fundingInstrument;
    }

    private HolderRequest createHolderRequest() {
        return new HolderRequest()
                .fullname(creditCardEcommerceFundingInstrument.getCreditCard().getHolder().getName())
                .birthdate(moipConfiguration.dateFromLocalDate(creditCardEcommerceFundingInstrument.getCreditCard().getHolder().getBirthdate()))
                .phone(new PhoneRequest()
                        .setAreaCode(creditCardEcommerceFundingInstrument.getCreditCard().getHolder().getPhone().getAreaCode())
                        .setNumber(creditCardEcommerceFundingInstrument.getCreditCard().getHolder().getPhone().getNumber())
                )
                .taxDocument(TaxDocumentRequest.cpf(creditCardEcommerceFundingInstrument.getCreditCard().getHolder().getTaxDocument().getNumber()));
    }

    @Override
    protected FundingInstrumentRequest createFundingInstrumentRequest() {
        HolderRequest holderRequest = createHolderRequest();

        CreditCardRequest creditCardRequest = new CreditCardRequest()
                .hash(creditCardEcommerceFundingInstrument.getCreditCard().getHash())
                .holder(holderRequest);

        return new FundingInstrumentRequest().creditCard(creditCardRequest);
    }

}
