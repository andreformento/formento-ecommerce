package com.formento.ecommerce.payment.model;

import com.formento.ecommerce.installment.model.InstallmentDefault;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class MethodPaymentInNTimesTest {

    @Test
    public void shouldHaveJustOneInstallment() {
        // given
        BigDecimal totalValue = BigDecimal.TEN;
        int count = 1;

        // when
        MethodPayment methodPayment = new MethodPaymentInNTimes(totalValue, count);

        // then
        assertEquals(count, methodPayment.getInstallments().size());
        assertEquals(new InstallmentDefault(totalValue), methodPayment.getInstallments().toArray()[0]);
    }

    @Test
    public void shouldHaveThreeInstallment() {
        // given
        BigDecimal totalValue = BigDecimal.TEN;
        int count = 3;

        // when
        MethodPayment methodPayment = new MethodPaymentInNTimes(totalValue, count);

        // then
        assertEquals(count, methodPayment.getInstallments().size());
        assertEquals(new InstallmentDefault(3.34), methodPayment.getInstallments().toArray()[0]);
        assertEquals(new InstallmentDefault(3.33), methodPayment.getInstallments().toArray()[1]);
        assertEquals(new InstallmentDefault(3.33), methodPayment.getInstallments().toArray()[1]);
    }

}
