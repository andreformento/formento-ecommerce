package com.formento.ecommerce.payment.service.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public abstract class AbstractPaymentRequest implements PaymentRequest {

    private Integer installmentCount;

}
