package com.formento.ecommerce.payment.model.boleto.template;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.formento.ecommerce.payment.model.boleto.BoletoEcommerceFundingInstrument;
import com.formento.ecommerce.payment.model.boleto.InstructionLines;

import java.time.LocalDate;

public class BoletoEcommerceFundingInstrumentTemplate implements TemplateLoader {

    public static String VALID = "valid";

    @Override
    public void load() {
        Fixture.of(BoletoEcommerceFundingInstrument.class)
                .addTemplate(VALID, new Rule() {{
                    add("expirationDate", LocalDate.now().plusYears(3));
                    add("instructionLines", one(InstructionLines.class, InstructionLinesTemplate.VALID));
                    add("logoUri", "http://ahcoloring.net/images/BmD3m4Jta.jpg");
                }});
    }

}
