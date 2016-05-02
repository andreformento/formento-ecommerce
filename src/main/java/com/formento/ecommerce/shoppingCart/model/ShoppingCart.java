package com.formento.ecommerce.shoppingCart.model;

import com.formento.ecommerce.user.model.User;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
@Entity
public class ShoppingCart implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private User user;

}
