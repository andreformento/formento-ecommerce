package com.formento.ecommerce.payment.model.creditCard.template;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.formento.ecommerce.payment.model.creditCard.CreditCardHolder;
import com.formento.ecommerce.payment.model.creditCard.TaxDocument;

import java.time.LocalDate;

public class CreditCardHolderTemplate implements TemplateLoader {

    public static String VALID = "valid";

    @Override
    public void load() {
        Fixture.of(CreditCardHolder.class)
                .addTemplate(VALID, new Rule() {{
                    add("name", "Andre Formento");
                    add("birthdate", LocalDate.of(1991, 3, 14));
                    add("taxDocument", one(TaxDocument.class, TaxDocumentTemplate.VALID));
                }});
    }

}
