package com.formento.ecommerce.ecommerceOrder.model.template;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.formento.ecommerce.ecommerceOrder.model.EcommerceOrder;
import com.formento.ecommerce.user.model.User;
import com.formento.ecommerce.user.model.template.UserTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;

public class EcommerceOrderTemplate implements TemplateLoader {

    public static String VALID_ORDER = "validOrder";

    @Override
    public void load() {
        Fixture.of(EcommerceOrder.class)
                .addTemplate(VALID_ORDER, new Rule() {{
                    add("id", 1l);
                    add("user", one(User.class, UserTemplate.VALID_USER));
                    add("itemOrders", new ArrayList<>());
                    add("totalValue", BigDecimal.TEN);
                    add("integrationId", "1");
                }});
    }

}
