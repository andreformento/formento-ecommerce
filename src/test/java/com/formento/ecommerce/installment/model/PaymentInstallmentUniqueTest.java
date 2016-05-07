package com.formento.ecommerce.installment.model;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class PaymentInstallmentUniqueTest {

    @Test
    public void shouldGiveToMeAnUniqueInstallment() {
        // given
        BigDecimal totalValue = BigDecimal.TEN;
        Installment installment = new InstallmentDefault(totalValue);

        // when
        PaymentInstallment paymentInstallment = new PaymentInstallmentUnique(totalValue);

        // then
        assertEquals(Integer.valueOf(1), paymentInstallment.getCount());
        assertEquals(BigDecimal.TEN, paymentInstallment.getTotalValue());
        assertEquals(1, paymentInstallment.getInstallments().size());
        assertEquals(installment, paymentInstallment.getInstallments().toArray()[0]);

    }

}