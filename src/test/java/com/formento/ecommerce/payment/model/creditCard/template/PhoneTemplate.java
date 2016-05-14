package com.formento.ecommerce.payment.model.creditCard.template;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.formento.ecommerce.payment.model.creditCard.CreditCardHolder;

public class PhoneTemplate implements TemplateLoader {

    public static String VALID = "valid";

    @Override
    public void load() {
        Fixture.of(CreditCardHolder.class)
                .addTemplate(VALID, new Rule() {{
                    add("areaCode", "11");
                    add("number", "55667788");
                }});
    }

}
