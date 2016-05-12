package com.formento.ecommerce.payment.model.boleto.template;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.formento.ecommerce.payment.model.boleto.InstructionLines;

public class InstructionLinesTemplate implements TemplateLoader {

    public static String VALID = "valid";

    @Override
    public void load() {
        Fixture.of(InstructionLines.class)
                .addTemplate(VALID, new Rule() {{
                    add("first", "First line");
                    add("second", "Second line");
                    add("third", "Third line");
                }});
    }

}
