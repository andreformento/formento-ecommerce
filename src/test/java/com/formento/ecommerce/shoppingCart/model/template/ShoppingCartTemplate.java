package com.formento.ecommerce.shoppingCart.model.template;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.formento.ecommerce.product.model.Product;
import com.formento.ecommerce.productPrice.model.ProductPriceEntity;
import com.formento.ecommerce.productPrice.model.template.ProductPriceTemplate;
import com.formento.ecommerce.shoppingCart.model.ItemShoppingCart;
import com.formento.ecommerce.shoppingCart.model.ShoppingCart;
import com.formento.ecommerce.user.model.User;
import com.formento.ecommerce.user.model.template.UserTemplate;
import com.google.common.collect.ImmutableList;

public class ShoppingCartTemplate implements TemplateLoader {

    public static String VALID_SHOPPING_CART_NO_ID = "validShoppingCartNoId";
    public static String VALID_SHOPPING_CART = "validShoppingCart";

    @Override
    public void load() {
        Fixture.of(ShoppingCart.class)
                .addTemplate(VALID_SHOPPING_CART, new Rule() {{
                    add("id", 2l);
                    add("user", one(User.class, UserTemplate.VALID_USER));
                    add("itemShoppingCarts", new ImmutableList.Builder<ItemShoppingCart>()
                            .add(new ItemShoppingCart(
                                    new Product.Builder().withAvailability(1).addProductPrice(Fixture.from(ProductPriceEntity.class).gimme(ProductPriceTemplate.VALID_CURRENT_PRODUCT_PRICE)).build(),
                                    1
                            ))
                            .add(new ItemShoppingCart(
                                    new Product.Builder().withAvailability(2).addProductPrice(Fixture.from(ProductPriceEntity.class).gimme(ProductPriceTemplate.VALID_TABLE_CURRENT_PRODUCT_PRICE)).build(),
                                    2
                            ))
                            .build());
                }})
                .addTemplate(VALID_SHOPPING_CART_NO_ID)
                .inherits(VALID_SHOPPING_CART, new Rule() {{
                    add("id", null);
                    add("user", one(User.class, UserTemplate.VALID_USER_NO_ID));
                }});
    }

}
