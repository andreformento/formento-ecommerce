package com.formento.ecommerce.payment.model.boleto;

import com.formento.ecommerce.payment.model.FundingInstrument;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serializable;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class BoletoFundingInstrument implements FundingInstrument, Serializable {

    @Override
    public String getMethod() {
        return "BOLETO";
    }

}
