package com.formento.ecommerce.payment.model;

import com.formento.ecommerce.installment.model.Installment;
import com.formento.ecommerce.installment.model.InstallmentDefault;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class PaymentRequestUniqueTest {

    @Test
    public void shouldGiveToMeAnUniqueInstallment() {
        // given
        BigDecimal totalValue = BigDecimal.TEN;
        Installment installment = new InstallmentDefault(totalValue);

        // when
        MethodPayment methodPayment = new UniqueMethodPayment(totalValue);

        // then
        assertEquals(Integer.valueOf(1), methodPayment.getCount());
        assertEquals(BigDecimal.TEN, methodPayment.getTotalValue());
        assertEquals(1, methodPayment.getInstallments().size());
        assertEquals(installment, methodPayment.getInstallments().toArray()[0]);

    }

}