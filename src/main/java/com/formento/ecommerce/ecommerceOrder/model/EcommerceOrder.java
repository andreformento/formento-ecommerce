package com.formento.ecommerce.ecommerceOrder.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.formento.ecommerce.discount.model.Coupon;
import com.formento.ecommerce.discount.model.CouponDiscountCalculator;
import com.formento.ecommerce.ecommerceOrder.model.converter.ItemOrdersSerializer;
import com.formento.ecommerce.shoppingCart.model.ItemShoppingCart;
import com.formento.ecommerce.shoppingCart.model.ShoppingCart;
import com.formento.ecommerce.user.model.User;
import com.formento.ecommerce.util.converter.OptionalLocalDateTimeSerializer;
import com.formento.ecommerce.util.converter.OptionalObjectSerializer;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class EcommerceOrder implements Serializable {

    @Id
    @GeneratedValue
    @JsonProperty
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @JsonSerialize(using = ItemOrdersSerializer.class)
    @OneToMany(mappedBy = "ecommerceOrder", cascade = CascadeType.ALL)
    @JsonProperty
    private Collection<ItemOrder> itemOrders;

    @NotNull
    private BigDecimal totalValue;

    private String integrationId;

    private LocalDateTime integrationDate;

    @ManyToOne
    private Coupon discountCoupon;

    @NotNull
    @Enumerated(EnumType.STRING)
    private StatusEcommerceOrder statusEcommerceOrder;

    @JsonSerialize(using = OptionalObjectSerializer.class)
    public Optional<String> getIntegrationId() {
        return Optional.ofNullable(this.integrationId);
    }

    @JsonSerialize(using = OptionalLocalDateTimeSerializer.class)
    public Optional<LocalDateTime> getIntegrationDate() {
        return Optional.ofNullable(this.integrationDate);
    }

    @JsonSerialize(using = OptionalLocalDateTimeSerializer.class)
    public Optional<Coupon> getDiscountCoupon() {
        return Optional.ofNullable(this.discountCoupon);
    }

    public BigDecimal getLiquidValue() {
        return getDiscountCoupon().map(coupon -> new CouponDiscountCalculator(coupon, getTotalValue(), LocalDate.now()).calculateLiquidValue()).orElse(getTotalValue());
    }

    public Coupon getCoupon() {
        return this.discountCoupon;
    }

    public Long getOrderId() {
        return this.id;
    }

    public static class Builder {

        private final EcommerceOrder instance = new EcommerceOrder();

        private Builder withTotalValue(Collection<ItemOrder> itemOrders) {
            instance.totalValue = itemOrders
                    .stream()
                    .map(ItemOrder::getTotalPrice)
                    .reduce(BigDecimal::add)
                    .orElse(BigDecimal.ZERO);
            return this;
        }

        private Builder withItemShoppingCarts(Collection<ItemShoppingCart> itemShoppingCarts) {
            instance.itemOrders = itemShoppingCarts
                    .stream()
                    .map(s -> s.generateItemOrder(instance))
                    .collect(Collectors.toList());
            return withTotalValue(instance.itemOrders);
        }

        private Builder withUser(User user) {
            instance.user = user;
            return this;
        }

        public Builder withShoppingCart(ShoppingCart shoppingCart) {
            return withItemShoppingCarts(shoppingCart.getItemShoppingCarts())
                    .withUser(shoppingCart.getUser());
        }

        public Builder withStatusEcommerceOrder(StatusEcommerceOrder statusEcommerceOrder) {
            instance.statusEcommerceOrder = statusEcommerceOrder;
            return this;
        }

        public Builder withIntegrationId(String integrationId) {
            instance.integrationId = integrationId;
            if (integrationId != null && (!integrationId.isEmpty())) {
                instance.integrationDate = LocalDateTime.now();
            }
            return this;
        }

        public Builder changeStatus(EcommerceOrder ecommerceOrderOld, EcommerceOrder ecommerceOrderToChange) {
            return withSelf(ecommerceOrderOld)
                    .withStatusEcommerceOrder(ecommerceOrderToChange.statusEcommerceOrder)
                    .withIntegrationId(ecommerceOrderToChange.integrationId);
        }

        public Builder withSelf(EcommerceOrder ecommerceOrder) {
            instance.id = ecommerceOrder.id;
            instance.user = ecommerceOrder.user;
            instance.itemOrders = ecommerceOrder.itemOrders;
            instance.totalValue = ecommerceOrder.totalValue;
            instance.integrationId = ecommerceOrder.integrationId;
            instance.integrationDate = ecommerceOrder.integrationDate;
            instance.discountCoupon = ecommerceOrder.discountCoupon;
            instance.statusEcommerceOrder = ecommerceOrder.statusEcommerceOrder;
            return this;
        }

        public Builder applyDiscount(Coupon discountCoupon) {
            instance.discountCoupon = discountCoupon.validateNow();
            return this;
        }

        public EcommerceOrder build() {
            return instance;
        }

    }

}
