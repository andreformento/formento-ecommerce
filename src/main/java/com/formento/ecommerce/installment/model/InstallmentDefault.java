package com.formento.ecommerce.installment.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@EqualsAndHashCode
@ToString
public class InstallmentDefault implements Installment {

    private final BigDecimal totalValue;

    public InstallmentDefault(BigDecimal totalValue) {
        this.totalValue = totalValue.setScale(2);
    }

    public InstallmentDefault(Double totalValue) {
        this(BigDecimal.valueOf(totalValue));
    }

}
