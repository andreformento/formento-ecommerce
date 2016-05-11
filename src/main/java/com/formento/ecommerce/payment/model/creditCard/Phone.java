package com.formento.ecommerce.payment.model.creditCard;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Phone implements Serializable {

    private String countryCode;
    private String areaCode;
    private String number;

}
