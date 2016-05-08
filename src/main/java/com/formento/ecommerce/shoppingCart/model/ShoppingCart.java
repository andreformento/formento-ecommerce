package com.formento.ecommerce.shoppingCart.model;

import com.formento.ecommerce.user.model.User;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ShoppingCart implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private User user;

    @NotNull
    @OneToMany(mappedBy = "shoppingCart")
    private Collection<ItemShoppingCart> itemShoppingCarts;

    public BigDecimal getTotalValue() {
        return itemShoppingCarts
                .stream()
                .map(ItemShoppingCart::getTotalPrice)
                .reduce(BigDecimal::add)
                .orElseGet(() -> BigDecimal.ZERO);
    }

}
