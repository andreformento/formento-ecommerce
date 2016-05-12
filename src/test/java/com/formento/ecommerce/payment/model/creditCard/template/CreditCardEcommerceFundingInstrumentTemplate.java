package com.formento.ecommerce.payment.model.creditCard.template;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.formento.ecommerce.payment.model.creditCard.CreditCard;
import com.formento.ecommerce.payment.model.creditCard.CreditCardEcommerceFundingInstrument;

public class CreditCardEcommerceFundingInstrumentTemplate implements TemplateLoader {

    public static String VALID = "valid";

    @Override
    public void load() {
        Fixture.of(CreditCardEcommerceFundingInstrument.class)
                .addTemplate(VALID, new Rule() {{
                    add("creditCard", one(CreditCard.class, CreditCardTemplate.VALID));
                }});
    }

}
