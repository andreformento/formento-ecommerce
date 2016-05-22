package com.formento.ecommerce.payment.service.request;

import com.formento.ecommerce.payment.model.boleto.BoletoEcommerceFundingInstrument;
import com.formento.ecommerce.payment.model.boleto.InstructionLines;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
public class BoletoPaymentRequest extends AbstractPaymentRequest implements PaymentRequest, Serializable {

    public BoletoPaymentRequest(Integer installmentCount) {
        super(installmentCount);
    }

    // fake instructions :)
    @Override
    public BoletoEcommerceFundingInstrument generateFundingInstrument() {
        return new BoletoEcommerceFundingInstrument(
                LocalDate.now().plusDays(7),
                new InstructionLines("Primeira linha", "Segunda linha", "Terceira linha"),
                "http://logo.com"
        );
    }

}
