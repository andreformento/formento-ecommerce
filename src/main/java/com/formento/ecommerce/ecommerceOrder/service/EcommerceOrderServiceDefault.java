package com.formento.ecommerce.ecommerceOrder.service;

import com.formento.ecommerce.discount.model.Coupon;
import com.formento.ecommerce.discount.service.CouponService;
import com.formento.ecommerce.ecommerceOrder.model.EcommerceOrder;
import com.formento.ecommerce.ecommerceOrder.repository.EcommerceOrderRepository;
import com.formento.ecommerce.exception.AccessDeniedEcommerceException;
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

    @Autowired
    private CouponService couponService;

    @Override
    public EcommerceOrder createIntegration() {
        return new MoipOrderIntegrationFacade(
                moipApi,
                this,
                shoppingCartService.finalizeCurrentFromUser()
        ).makeOrder();
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

    @Override
    public EcommerceOrder applyDiscount(Long orderId, Coupon discountCoupon) {
        return ecommerceOrderRepository.save(
                new EcommerceOrder
                        .Builder()
                        .withSelf(getValidatedOrderById(orderId))
                        .applyDiscount(couponService.getCouponByCode(discountCoupon.getCode()))
                        .build()
        );
    }

    @Override
    public Optional<EcommerceOrder> getOrderById(Long orderId) {
        return Optional
                .ofNullable(ecommerceOrderRepository.findOne(orderId))
                .map(ecommerceOrder -> {
                    if (!ecommerceOrder.getUser().getEmail().equals(userService.getValidatedUserOfSession().getEmail())) {
                        throw new AccessDeniedEcommerceException("ecommerceOrder.accessDenied");
                    }
                    return ecommerceOrder;
                });
    }

    @Override
    public EcommerceOrder getValidatedOrderById(Long orderId) {
        return getOrderById(orderId).orElseThrow(() -> new BusinessEcommerceException("ecommerceOrder.notFound"));
    }

}
