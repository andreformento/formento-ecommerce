package com.formento.ecommerce.installment.model;

import com.google.common.collect.ImmutableList;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class PaymentInstallmentInNTimes implements PaymentInstallment, Serializable {

    private BigDecimal totalValue;
    private Integer count;

    @Override
    public Integer getCount() {
        return count;
    }

    @Override
    public Collection<Installment> getInstallments() {
        return divide();
    }

    private ImmutableList<Installment> divide() {
        InstallmentCalculatorInNTimes calculator = new InstallmentCalculatorInNTimes(totalValue, count);

        ImmutableList.Builder<Installment> installmentsBuiler = new ImmutableList.Builder<>();

        installmentsBuiler.add(calculator.getFirstInstallment());

        calculator
                .getInstallments()
                .map(installment -> Stream
                        .generate(() -> installment)
                        .limit(count - 1)
                        .collect(toList())
                )
                .ifPresent(installmentsBuiler::addAll);

        return installmentsBuiler.build();
    }

}
