package com.formento.ecommerce.integration2;

import com.formento.ecommerce.installment.model.PaymentInstallment;
import com.formento.ecommerce.payment.model.PaymentEcommerce;
import com.formento.ecommerce.payment.model.creditCard.CreditCardHolder;
import com.formento.ecommerce.shoppingCart.model.ShoppingCart;

public interface CreditCardPaymentFacade {

    PaymentEcommerce makePayment(ShoppingCart shoppingCart, PaymentInstallment paymentInstallment, CreditCardHolder creditCardHolder);

}
