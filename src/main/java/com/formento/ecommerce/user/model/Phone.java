package com.formento.ecommerce.user.model;

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
public class Phone implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private User user;

    private Boolean activated = true;

    private String countryCode;
    private String areaCode;
    private String number;

}
