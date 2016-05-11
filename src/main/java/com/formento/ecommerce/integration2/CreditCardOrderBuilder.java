package com.formento.ecommerce.integration2;

import com.formento.ecommerce.shoppingCart.model.ShoppingCart;

public interface CreditCardOrderBuilder {

    CreditCardOrderBuilder withShoppingCart(ShoppingCart shoppingCart);

}
