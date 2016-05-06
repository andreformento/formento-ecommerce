package com.formento.ecommerce.discount.model;

import java.math.BigDecimal;

public interface Discount {

    Long getId();

    String getDescription();

    BigDecimal getValue();

    BigDecimal getLiquidValue();

}
