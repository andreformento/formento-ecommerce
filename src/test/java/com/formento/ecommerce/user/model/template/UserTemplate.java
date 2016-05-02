package com.formento.ecommerce.user.model.template;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.formento.ecommerce.user.model.User;

public class UserTemplate implements TemplateLoader {

    public static String VALID_USER = "validUser";

    @Override
    public void load() {
        Fixture.of(User.class).addTemplate(VALID_USER, new Rule() {{
            add("id", 1l);
            add("name", "Andre Formento");
            add("email", "andreformento.sc@gmail.com");
            add("password", "myP@ssw0rd");
            add("token", "token");
        }})/*.addTemplate(VALID_USER, new Rule() {{
            add("token", "token");
        }})*/;
    }

}