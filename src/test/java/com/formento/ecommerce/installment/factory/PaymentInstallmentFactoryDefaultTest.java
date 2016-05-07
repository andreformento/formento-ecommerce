package com.formento.ecommerce.installment.factory;

import com.formento.ecommerce.exception.BusinessEcommerceException;
import com.formento.ecommerce.exception.DiscountEcommerceException;
import com.formento.ecommerce.installment.model.PaymentInstallment;
import com.formento.ecommerce.installment.model.PaymentInstallmentInNTimes;
import com.formento.ecommerce.installment.model.PaymentInstallmentUnique;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;

import static org.junit.Assert.assertTrue;

public class PaymentInstallmentFactoryDefaultTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private PaymentInstallmentFactoryDefault paymentInstallmentFactoryDefault;

    @Before
    public void init() {
        this.paymentInstallmentFactoryDefault = new PaymentInstallmentFactoryDefault();
    }

    @Test
    public void shouldBeGeneratePaymentInstallmentUnique() {
        // given
        BigDecimal totalValue = BigDecimal.TEN;
        Integer count = 1;

        // when
        PaymentInstallment paymentInstallment = paymentInstallmentFactoryDefault.makePaymentInstallment(totalValue, count);

        // then
        assertTrue(paymentInstallment instanceof PaymentInstallmentUnique);
    }

    @Test
    public void shouldBeGeneratePaymentInstallmentInNTimes() {
        // given
        BigDecimal totalValue = BigDecimal.TEN;
        Integer count = 12;

        // when
        PaymentInstallment paymentInstallment = paymentInstallmentFactoryDefault.makePaymentInstallment(totalValue, count);

        // then
        assertTrue(paymentInstallment instanceof PaymentInstallmentInNTimes);
    }

    @Test
    public void shouldNotBeGeneratedWithInvalidCount() {
        // given
        BigDecimal totalValue = BigDecimal.TEN;
        Integer count = 13;

        // then
        expectedException.expect(BusinessEcommerceException.class);
        expectedException.expectMessage("paymentInstallment.quantity.notAccepted");

        // when
        PaymentInstallment paymentInstallment = paymentInstallmentFactoryDefault.makePaymentInstallment(totalValue, count);
    }

}
