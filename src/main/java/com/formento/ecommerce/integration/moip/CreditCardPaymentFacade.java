package com.formento.ecommerce.integration.moip;

import br.com.moip.request.*;
import com.formento.ecommerce.ecommerceOrder.model.EcommerceOrder;
import com.formento.ecommerce.integration.PaymentFacade;
import com.formento.ecommerce.payment.model.MethodPayment;
import com.formento.ecommerce.payment.model.creditCard.CreditCard;
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
        CreditCard creditCard = creditCardEcommerceFundingInstrument.getCreditCard();
        return new HolderRequest()
                .fullname(creditCard.getHolder().getName())
                .birthdate(moipConfiguration.dateFromLocalDate(creditCard.getHolder().getBirthdate()))
                .phone(new PhoneRequest()
                        .setAreaCode(creditCard.getHolder().getPhone().getAreaCode())
                        .setNumber(creditCard.getHolder().getPhone().getNumber())
                )
                .taxDocument(TaxDocumentRequest.cpf(creditCard.getHolder().getTaxDocument().getNumber()));
    }


    @Override
    protected FundingInstrumentRequest createFundingInstrumentRequest() {
        HolderRequest holderRequest = createHolderRequest();

        CreditCardRequest creditCardRequest = new CreditCardRequest()
//                .hash(creditCardEcommerceFundingInstrument.getCreditCard().getHash())
                .hash("HhL0kbhfid+jwgj5l6Kt9EPdetDxQN8s7uKUHDYxDC/XoULjzik44rSda3EcWuOcL17Eb8JjWc1JI7gsuwg9P0rJv1mJQx+d3Dv1puQYz1iRjEWWhnB1bw0gTvnnC/05KbWN5M8oTiugmhVK02Rt2gpbcTtpS7VWyacfgesBJFavYYMljYg8p2YGHXkXrMuQiOCeemKLk420d0OTMBba27jDVVJ663HZDrObnjFXJH/4B5irkj+HO5genV+V4PYoLcOESG4nrI3oFAsMGsLLcdJo0NNvkEmJpn0e9GzureKKFYisYU+BEd9EMr/odS0VMvOYRV65HbPTspIkjl2+3Q==") //fake
                .holder(holderRequest);

        return new FundingInstrumentRequest().creditCard(creditCardRequest);
    }

}
