package com.formento.ecommerce.integration.moip;

import br.com.moip.API;
import br.com.moip.request.CustomerRequest;
import br.com.moip.request.OrderRequest;
import br.com.moip.resource.Order;
import com.formento.ecommerce.ecommerceOrder.model.EcommerceOrder;
import com.formento.ecommerce.ecommerceOrder.model.StatusEcommerceOrder;
import com.formento.ecommerce.ecommerceOrder.service.EcommerceOrderService;
import com.formento.ecommerce.integration.OrderFacade;
import com.formento.ecommerce.shoppingCart.model.ShoppingCart;
import com.formento.ecommerce.user.model.User;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;

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

        EcommerceOrder ecommerceOrder = initializeLocalEcommerceOrder();

        Order moipOrder = getOrderRequest(customerOwnId, ecommerceOrder, api, ecommerceOrder.getId().toString());

        return changeToCreated(ecommerceOrder.getId(), moipOrder.getId());
    }

    private EcommerceOrder initializeLocalEcommerceOrder() {
        return ecommerceOrderService.save(
                new EcommerceOrder.Builder()
                        .withShoppingCart(shoppingCart)
                        .withStatusEcommerceOrder(StatusEcommerceOrder.SENT)
                        .build()
        );
    }

    private EcommerceOrder changeToCreated(Long orderId, String integrationId) {
        return ecommerceOrderService.changeStatusOrder(orderId,
                new EcommerceOrder.Builder()
                        .withStatusEcommerceOrder(StatusEcommerceOrder.CREATED)
                        .withIntegrationId(integrationId)
                        .build()
        );
    }

    private Order getOrderRequest(CustomerRequest customerRequest, EcommerceOrder ecommerceOrder, API api, String ecommerceOrderId) {
        OrderRequest orderRequest = new OrderRequest()
                .ownId(ecommerceOrderId)
                .customer(customerRequest);

        ecommerceOrder
                .getItemOrders()
                .stream()
                .forEach(itemOrder ->
                        orderRequest.addItem(
                                itemOrder.getProduct().getName(),
                                itemOrder.getQuantity(),
                                itemOrder.getProduct().getDescription(),
                                itemOrder.getTotalPrice().multiply(BigDecimal.valueOf(100)).setScale(0, RoundingMode.DOWN).intValue()
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
