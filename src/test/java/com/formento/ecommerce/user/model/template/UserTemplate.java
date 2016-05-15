package com.formento.ecommerce.user.model.template;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.formento.ecommerce.user.model.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class UserTemplate implements TemplateLoader {

    public static String VALID_USER = "validUser";
    public static String VALID_USER_NO_ID = "validUserNoId";

    @Override
    public void load() {
        Fixture.of(User.class)
                .addTemplate(VALID_USER, new Rule() {{
                    add("uuid", UUID.randomUUID());
                    add("name", "Andre Formento");
                    add("email", "andreformento.sc@gmail.com");
                    add("password", "myP@ssw0rd");
                    add("token", "token");
                    add("creationDate", LocalDate.now());
                    add("updateDate", LocalDate.now());
                    add("lastLogin", LocalDateTime.now());
                }})
                .addTemplate(VALID_USER_NO_ID)
                .inherits(VALID_USER, new Rule() {{
                    add("uuid", null);
                }});
    }

}