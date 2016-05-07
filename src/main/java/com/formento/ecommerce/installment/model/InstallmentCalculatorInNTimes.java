package com.formento.ecommerce.installment.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class InstallmentCalculatorInNTimes {

    private static int SCALE = 2;

    private BigDecimal totalValue;
    private Integer count;

    public Installment getFirstInstallment() {
        return new InstallmentDefault(calculateFirst());
    }

    public Optional<Installment> getInstallments() {
        return count <= 1 ?
                Optional.empty() :
                Optional.of(new InstallmentDefault(calculateInstallments()));
    }

    private BigDecimal calculateFirst() {
        return totalValue.subtract(calculateInstallments().multiply(BigDecimal.valueOf(count - 1)));
    }

    private BigDecimal calculateInstallments() {
        return totalValue
                .setScale(SCALE, RoundingMode.DOWN)
                .divide(BigDecimal.valueOf(count), SCALE, RoundingMode.DOWN);
    }

}
