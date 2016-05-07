package com.formento.ecommerce.installment.model;

import com.google.common.collect.ImmutableList;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class PaymentInstallmentUnique implements PaymentInstallment, Serializable {

    private static int COUNT_OF_UNIQUE_INSTALLMENT = 1;

    private BigDecimal totalValue;

    @Override
    public Integer getCount() {
        return COUNT_OF_UNIQUE_INSTALLMENT;
    }

    @Override
    public Collection<Installment> getInstallments() {
        return ImmutableList.of(new InstallmentDefault(totalValue));
    }

}
