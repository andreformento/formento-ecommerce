package com.formento.ecommerce.installment.model;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class PaymentInstallmentInNTimesTest {

    @Test
    public void shouldHaveJustOneInstallment() {
        // given
        BigDecimal totalValue = BigDecimal.TEN;
        int count = 1;

        // when
        PaymentInstallment paymentInstallment = new PaymentInstallmentInNTimes(totalValue, count);

        // then
        assertEquals(count, paymentInstallment.getInstallments().size());
        assertEquals(new InstallmentDefault(totalValue), paymentInstallment.getInstallments().toArray()[0]);
    }

    @Test
    public void shouldHaveThreeInstallment() {
        // given
        BigDecimal totalValue = BigDecimal.TEN;
        int count = 3;

        // when
        PaymentInstallment paymentInstallment = new PaymentInstallmentInNTimes(totalValue, count);

        // then
        assertEquals(count, paymentInstallment.getInstallments().size());
        assertEquals(new InstallmentDefault(3.34), paymentInstallment.getInstallments().toArray()[0]);
        assertEquals(new InstallmentDefault(3.33), paymentInstallment.getInstallments().toArray()[1]);
        assertEquals(new InstallmentDefault(3.33), paymentInstallment.getInstallments().toArray()[1]);
    }

}
