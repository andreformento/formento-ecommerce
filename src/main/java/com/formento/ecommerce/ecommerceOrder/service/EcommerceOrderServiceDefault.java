package com.formento.ecommerce.ecommerceOrder.service;

import com.formento.ecommerce.ecommerceOrder.model.EcommerceOrder;
import com.formento.ecommerce.ecommerceOrder.repository.EcommerceOrderRepository;
import com.formento.ecommerce.exception.BusinessEcommerceException;
import com.formento.ecommerce.integration.moip.MoipApi;
import com.formento.ecommerce.integration.moip.MoipOrderIntegrationFacade;
import com.formento.ecommerce.shoppingCart.service.ShoppingCartService;
import com.formento.ecommerce.user.model.service.UserService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@NoArgsConstructor
@Service
public class EcommerceOrderServiceDefault implements EcommerceOrderService {

    @Autowired
    private EcommerceOrderRepository ecommerceOrderRepository;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private UserService userService;

    @Autowired
    private MoipApi moipApi;

    @Override
    public EcommerceOrder createIntegration() {
        return new MoipOrderIntegrationFacade(
                moipApi,
                this,
                shoppingCartService.finalizeCurrentFromUser()
        ).makeOrder();
    }

    @Override
    public Optional<EcommerceOrder> getCurrentOrder() {
        return ecommerceOrderRepository.getCurrentOrder(userService.loadUserValidated().getEmail());
    }

    @Override
    public EcommerceOrder getValidatedCurrentOrder() {
        return getCurrentOrder().orElseThrow(() -> new BusinessEcommerceException("order.currrentOrderNotFound"));
    }

    @Override
    public EcommerceOrder save(EcommerceOrder ecommerceOrder) {
        return ecommerceOrderRepository.save(ecommerceOrder);
    }

    @Override
    public EcommerceOrder changeStatusOrder(Long orderId, EcommerceOrder ecommerceOrderToChange) {
        return ecommerceOrderRepository.save(
                new EcommerceOrder
                        .Builder()
                        .changeStatus(
                                Optional.ofNullable(ecommerceOrderRepository.findOne(orderId))
                                        .orElseThrow(() -> new BusinessEcommerceException("ecommerceOrder.cannotChangeStatusBecauseDotNotExists")),
                                ecommerceOrderToChange)
                        .build()
        );
    }

}
