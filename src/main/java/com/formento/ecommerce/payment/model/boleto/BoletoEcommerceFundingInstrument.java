package com.formento.ecommerce.payment.model.boleto;

import com.formento.ecommerce.payment.model.FundingInstrument;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class BoletoEcommerceFundingInstrument implements FundingInstrument, Serializable {

    private LocalDate expirationDate;
    private InstructionLines instructionLines;
    private String logoUri;

    @Override
    public String getMethod() {
        return "BOLETO";
    }

    public class Builder {
        private BoletoEcommerceFundingInstrument instance = new BoletoEcommerceFundingInstrument();

        public Builder withExpirationDate(LocalDate expirationDate) {
            instance.expirationDate = expirationDate;
            return this;
        }

        public Builder withInstructionLines(InstructionLines instructionLines) {
            instance.instructionLines = instructionLines;
            return this;
        }

        public Builder withLogoUri(String logoUri) {
            instance.logoUri = logoUri;
            return this;
        }

        public BoletoEcommerceFundingInstrument build() {
            return this.instance;
        }
    }

}
