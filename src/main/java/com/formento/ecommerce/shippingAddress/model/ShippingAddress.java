package com.formento.ecommerce.shippingAddress.model;

import com.formento.ecommerce.user.model.User;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ShippingAddress implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private User user;

    private Boolean activated = true;

    private String street;
    private String streetNumber;
    private String complement;
    private String district;
    private String city;
    private String state;
    private String country;
    private String zipCode;

}
