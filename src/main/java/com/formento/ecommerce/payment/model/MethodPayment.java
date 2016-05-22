package com.formento.ecommerce.payment.model;

import com.formento.ecommerce.installment.model.Installment;

import java.math.BigDecimal;
import java.util.Collection;

public interface MethodPayment {

    BigDecimal getTotalValue();

    Integer getCount();

    Collection<? extends Installment> getInstallments();

}
