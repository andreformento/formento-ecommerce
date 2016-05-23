package com.formento.ecommerce.payment.model.creditCard;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class TaxDocument implements Serializable {

    @NotNull
    private Type type;

    // validator
    @Transient
    private String number;

    public enum Type {
        CPF, CNPJ
    }

}
