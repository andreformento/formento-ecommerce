package com.formento.ecommerce.installment.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Getter
@EqualsAndHashCode
@ToString
public class InstallmentDefault implements Installment {

    private final BigDecimal totalValue;

    public InstallmentDefault(BigDecimal totalValue) {
        this.totalValue = totalValue.setScale(2, RoundingMode.DOWN);
    }

    public InstallmentDefault(Double totalValue) {
        this(BigDecimal.valueOf(totalValue));
    }

}
