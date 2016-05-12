package com.formento.ecommerce.payment.model.creditCard.template;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.formento.ecommerce.payment.model.creditCard.TaxDocument;

public class TaxDocumentTemplate implements TemplateLoader {

    public static String VALID = "valid";

    @Override
    public void load() {
        Fixture.of(TaxDocument.class)
                .addTemplate(VALID, new Rule() {{
                    add("type", TaxDocument.Type.CPF);
                    add("number", "22222222222");
                }});
    }

}
