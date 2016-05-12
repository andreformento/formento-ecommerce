package com.formento.ecommerce.installment.factory;

import com.formento.ecommerce.exception.BusinessEcommerceException;
import com.formento.ecommerce.payment.model.MethodPayment;
import com.formento.ecommerce.payment.model.MethodPaymentInNTimes;
import com.formento.ecommerce.payment.model.UniqueMethodPayment;
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
        MethodPayment paymentRequest = paymentInstallmentFactoryDefault.makeMethodPayment(totalValue, count);

        // then
        assertTrue(paymentRequest instanceof UniqueMethodPayment);
    }

    @Test
    public void shouldBeGeneratePaymentInstallmentInNTimes() {
        // given
        BigDecimal totalValue = BigDecimal.TEN;
        Integer count = 12;

        // when
        MethodPayment methodPayment = paymentInstallmentFactoryDefault.makeMethodPayment(totalValue, count);

        // then
        assertTrue(methodPayment instanceof MethodPaymentInNTimes);
    }

    @Test
    public void shouldNotBeGeneratedWithInvalidCount() {
        // given
        BigDecimal totalValue = BigDecimal.TEN;
        Integer count = 13;

        // then
        expectedException.expect(BusinessEcommerceException.class);
        expectedException.expectMessage("methodPayment.quantity.notAccepted");

        // when
        paymentInstallmentFactoryDefault.makeMethodPayment(totalValue, count);
    }

}
