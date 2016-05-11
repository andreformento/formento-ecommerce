package com.formento.ecommerce.integration2.moip;

import br.com.moip.API;
import br.com.moip.request.*;
import br.com.moip.resource.Order;
import com.formento.ecommerce.ecommerceOrder.model.EcommerceOrder;
import com.formento.ecommerce.ecommerceOrder.model.StatusEcommerceOrder;
import com.formento.ecommerce.ecommerceOrder.service.EcommerceOrderService;
import com.formento.ecommerce.shoppingCart.model.ShoppingCart;
import com.formento.ecommerce.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

//@Component
public class MoipOrder {//implements CreditCardOrderFacade {
//
//    @Autowired
//    private MoipConfiguration moipConfiguration;
//
//    @Autowired
//    private MoipApi moipApi;
//
//    @Autowired
//    private EcommerceOrderService ecommerceOrderService;
//
//    @Override
//    public StatusEcommerceOrder makeOrder(ShoppingCart shoppingCart) {
//        User user = shoppingCart.getUser();
//        CustomerRequest customerOwnId = getCustomerRequest(user);
//
//        API api = moipApi.getApi();
//
//        EcommerceOrder ecommerceOrder = getEcommerceOrder(shoppingCart);
//
//        return getOrderRequest(shoppingCart, customerOwnId, api, ecommerceOrder);
//
//        createPayment(creditCardHolder, paymentInstallment, api, createdOrder);
//
//        return ecommerceOrder;
//    }
//
//    private EcommerceOrder getEcommerceOrder(ShoppingCart shoppingCart) {
//        return ecommerceOrderService.create(
//                new EcommerceOrder.Builder()
//                        .withShoppingCart(shoppingCart)
//                        .withStatusEcommerceOrder(StatusEcommerceOrder.CREATED)
//                        .build()
//        );
//    }
//
//    private Order getOrderRequest(ShoppingCart shoppingCart, CustomerRequest customerOwnId, API api, EcommerceOrder ecommerceOrder) {
//        OrderRequest orderRequest = new OrderRequest()
//                .ownId(ecommerceOrder.getId().toString())
//                .customer(customerOwnId);
//        shoppingCart
//                .getItemShoppingCarts()
//                .stream()
//                .forEach(itemShoppingCart ->
//                        orderRequest.addItem(
//                                itemShoppingCart.getProduct().getName(),
//                                itemShoppingCart.getQuantity(),
//                                itemShoppingCart.getProduct().getDescription(),
//                                itemShoppingCart.getProductPrice().multiply(BigDecimal.valueOf(100)).setScale(0, RoundingMode.DOWN).intValue()
//                        ));
//
//        return api.order().create(orderRequest);
//    }
//
//    private CustomerRequest getCustomerRequest(User user) {
//        // Criando um Pedido
//        return new CustomerRequest()
//                .ownId(user.getId().toString())
//                .fullname(user.getName())
//                .email(user.getEmail());
//    }

}
