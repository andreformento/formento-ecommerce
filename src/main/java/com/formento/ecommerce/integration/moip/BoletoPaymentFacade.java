package com.formento.ecommerce.integration.moip;

import br.com.moip.request.ApiDateRequest;
import br.com.moip.request.BoletoRequest;
import br.com.moip.request.FundingInstrumentRequest;
import br.com.moip.request.InstructionLinesRequest;
import com.formento.ecommerce.ecommerceOrder.model.EcommerceOrder;
import com.formento.ecommerce.integration.PaymentFacade;
import com.formento.ecommerce.payment.model.MethodPayment;
import com.formento.ecommerce.payment.model.boleto.BoletoEcommerceFundingInstrument;
import com.formento.ecommerce.payment.model.boleto.InstructionLines;
import com.formento.ecommerce.util.LocalDateUtil;

public class BoletoPaymentFacade extends AbstractPaymentFacade implements PaymentFacade {

    private final BoletoEcommerceFundingInstrument boletoEcommerceFundingInstrument;

    public BoletoPaymentFacade(MoipApi moipApi, EcommerceOrder ecommerceOrder, MethodPayment methodPayment, BoletoEcommerceFundingInstrument fundingInstrument) {
        super(moipApi, ecommerceOrder, methodPayment, fundingInstrument);
        this.boletoEcommerceFundingInstrument = fundingInstrument;
    }

    private InstructionLinesRequest createInstructionLinesRequest() {
        InstructionLines instructionLines = boletoEcommerceFundingInstrument.getInstructionLines();

        return new InstructionLinesRequest()
                .first(instructionLines.getFirst())
                .second(instructionLines.getSecond())
                .third(instructionLines.getThird());
    }

    @Override
    protected FundingInstrumentRequest createFundingInstrumentRequest() {
        BoletoRequest boletoRequest = new BoletoRequest()
                .expirationDate(new ApiDateRequest().date(LocalDateUtil.toDate(boletoEcommerceFundingInstrument.getExpirationDate())))
                .logoUri(boletoEcommerceFundingInstrument.getLogoUri())
                .instructionLines(createInstructionLinesRequest());

        return new FundingInstrumentRequest().boleto(boletoRequest);
    }

}
