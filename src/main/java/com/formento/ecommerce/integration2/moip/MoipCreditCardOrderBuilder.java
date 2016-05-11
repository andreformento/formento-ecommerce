package com.formento.ecommerce.integration2.moip;

import br.com.moip.API;
import br.com.moip.request.CustomerRequest;
import br.com.moip.request.OrderRequest;
import br.com.moip.resource.Order;
import com.formento.ecommerce.ecommerceOrder.model.EcommerceOrder;
import com.formento.ecommerce.ecommerceOrder.service.EcommerceOrderService;
import com.formento.ecommerce.integration2.CreditCardOrderBuilder;
import com.formento.ecommerce.shoppingCart.model.ShoppingCart;
import com.formento.ecommerce.user.model.User;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MoipCreditCardOrderBuilder implements CreditCardOrderBuilder {

    private final MoipConfiguration moipConfiguration;
    private final MoipApi moipApi;
    private final EcommerceOrderService ecommerceOrderService;

    public MoipCreditCardOrderBuilder(MoipConfiguration moipConfiguration, MoipApi moipApi, EcommerceOrderService ecommerceOrderService) {
        this.moipConfiguration = moipConfiguration;
        this.moipApi = moipApi;
        this.ecommerceOrderService = ecommerceOrderService;
    }

    private EcommerceOrder ecommerceOrder;

    @Override
    public CreditCardOrderBuilder withShoppingCart(ShoppingCart shoppingCart) {
        User user = shoppingCart.getUser();
        CustomerRequest customerOwnId = getCustomerRequest(user);

        API api = moipApi.getApi();

//        ecommerceOrder = getEcommerceOrder(shoppingCart);

        return this;
//        return getOrderRequest(shoppingCart, customerOwnId, api, ecommerceOrder);
    }

//    private EcommerceOrder getEcommerceOrder(ShoppingCart shoppingCart) {
//        return ecommerceOrderService.create(
//                new EcommerceOrder.Builder()
//                        .withShoppingCart(shoppingCart)
//                        .withStatusEcommerceOrder(StatusEcommerceOrder.CREATED)
//                        .build()
//        );
//    }

    private Order getOrderRequest(ShoppingCart shoppingCart, CustomerRequest customerOwnId, API api, EcommerceOrder ecommerceOrder) {
        OrderRequest orderRequest = new OrderRequest()
                .ownId(ecommerceOrder.getId().toString())
                .customer(customerOwnId);
        shoppingCart
                .getItemShoppingCarts()
                .stream()
                .forEach(itemShoppingCart ->
                        orderRequest.addItem(
                                itemShoppingCart.getProduct().getName(),
                                itemShoppingCart.getQuantity(),
                                itemShoppingCart.getProduct().getDescription(),
                                itemShoppingCart.getProductPrice().multiply(BigDecimal.valueOf(100)).setScale(0, RoundingMode.DOWN).intValue()
                        ));

        return api.order().create(orderRequest);
    }

    private CustomerRequest getCustomerRequest(User user) {
        // Criando um Pedido
        return new CustomerRequest()
                .ownId(user.getId().toString())
                .fullname(user.getName())
                .email(user.getEmail());
    }

}
