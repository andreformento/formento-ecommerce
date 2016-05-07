package com.formento.ecommerce.installment.model;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class InstallmentCalculatorInNTimesTest {

    @Test
    public void shouldCalculateTotalValueForUniqueInstallment() {
        // given
        BigDecimal totalValue = BigDecimal.TEN;
        int count = 1;
        Installment installmentExpected = new InstallmentDefault(totalValue);

        // when
        InstallmentCalculatorInNTimes calculator = new InstallmentCalculatorInNTimes(totalValue, count);

        // then
        assertEquals(installmentExpected, calculator.getFirstInstallment());
        assertEquals(Optional.empty(), calculator.getInstallments());
    }

    @Test
    public void shouldHaveEqualValueOfInstallment() {
        // given
        BigDecimal totalValue = BigDecimal.TEN;
        int count = 4;
        Installment installmentExpected = new InstallmentDefault(BigDecimal.valueOf(2.5));

        // when
        InstallmentCalculatorInNTimes calculator = new InstallmentCalculatorInNTimes(totalValue, count);

        // then
        assertEquals(installmentExpected, calculator.getFirstInstallment());
        assertNotEquals(Optional.empty(), calculator.getInstallments());
        assertEquals(installmentExpected, calculator.getInstallments().get());
    }

    @Test
    public void shouldHaveFirstValueGreaterThanOthers() {
        // given
        BigDecimal totalValue = BigDecimal.TEN;
        int count = 3;
        Installment firstInstallmentExpected = new InstallmentDefault(BigDecimal.valueOf(3.34));
        Installment othersInstallmentExpected = new InstallmentDefault(BigDecimal.valueOf(3.33));

        // when
        InstallmentCalculatorInNTimes calculator = new InstallmentCalculatorInNTimes(totalValue, count);

        // then
        assertEquals(firstInstallmentExpected, calculator.getFirstInstallment());
        assertNotEquals(Optional.empty(), calculator.getInstallments());
        assertEquals(othersInstallmentExpected, calculator.getInstallments().get());
    }

}
