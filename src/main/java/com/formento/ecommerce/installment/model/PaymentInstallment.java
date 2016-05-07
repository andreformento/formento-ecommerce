package com.formento.ecommerce.installment.model;

import java.math.BigDecimal;
import java.util.Collection;

public interface PaymentInstallment {

    BigDecimal getTotalValue();

    Integer getCount();

    Collection<Installment> getInstallments();

}
