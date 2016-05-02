package com.formento.ecommerce.shoppingCart.model.template;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.formento.ecommerce.product.model.Product;
import com.formento.ecommerce.shoppingCart.model.ShoppingCart;
import com.formento.ecommerce.user.model.User;
import com.formento.ecommerce.user.model.template.UserTemplate;

import java.util.ArrayList;

public class ShoppingCartTemplate implements TemplateLoader {

    public static String VALID_SHOPPING_CART = "validShoppingCart";

    @Override
    public void load() {
        Fixture.of(ShoppingCart.class).addTemplate(VALID_SHOPPING_CART, new Rule() {{
            add("id", 2l);
            add("user", one(User.class, UserTemplate.VALID_USER));
            add("products", new ArrayList<Product>());
        }});
    }

}
