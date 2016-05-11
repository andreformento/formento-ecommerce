package com.formento.ecommerce.integration.moip;

import br.com.moip.API;
import br.com.moip.Client;
import br.com.moip.authentication.Authentication;
import br.com.moip.authentication.BasicAuth;
import br.com.moip.request.*;
import br.com.moip.resource.Order;
import br.com.moip.resource.Payment;
import com.formento.ecommerce.ecommerceOrder.model.EcommerceOrder;
import com.formento.ecommerce.ecommerceOrder.model.StatusEcommerceOrder;
import com.formento.ecommerce.ecommerceOrder.service.EcommerceOrderService;
import com.formento.ecommerce.integration.OrderFacade;
import com.formento.ecommerce.integration2.moip.MoipApi;
import com.formento.ecommerce.shoppingCart.model.ShoppingCart;
import com.formento.ecommerce.user.model.User;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.UUID;

@AllArgsConstructor
public class MoipOrderIntegrationFacade implements OrderFacade {

    private final MoipApi moipApi;
    private final EcommerceOrderService ecommerceOrderService;
    private final ShoppingCart shoppingCart;

    @Override
    public EcommerceOrder makeOrder() {
        User user = shoppingCart.getUser();
        CustomerRequest customerOwnId = getCustomerRequest(user);

        API api = moipApi.getApi();

        EcommerceOrder ecommerceOrder = createLocalEcommerceOrder(shoppingCart);

        Order moipOrder = getOrderRequest(shoppingCart, customerOwnId, api, ecommerceOrder);

        return changeToCreated(ecommerceOrder, moipOrder.getId());
    }

    private EcommerceOrder createLocalEcommerceOrder(ShoppingCart shoppingCart) {
        return ecommerceOrderService.create(
                new EcommerceOrder.Builder()
                        .withShoppingCart(shoppingCart)
                        .withStatusEcommerceOrder(StatusEcommerceOrder.SENT)
                        .build()
        );
    }

    private EcommerceOrder changeToCreated(EcommerceOrder ecommerceOrder, String integrationId) {
        return ecommerceOrderService.changeStatusOrder(ecommerceOrder, integrationId, StatusEcommerceOrder.CREATED);
    }

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
