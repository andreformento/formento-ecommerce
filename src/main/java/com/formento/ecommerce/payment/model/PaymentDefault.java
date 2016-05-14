package com.formento.ecommerce.payment.model;

import com.formento.ecommerce.ecommerceOrder.model.EcommerceOrder;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Optional;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
public final class PaymentDefault implements Payment {
    private MethodPayment methodPayment;
    private FundingInstrument fundingInstrument;
    private Optional<EcommerceOrder> ecommerceOrder;

    public PaymentDefault() {
        ecommerceOrder = Optional.empty();
    }

    public static class Builder {
        private PaymentDefault instance = new PaymentDefault();

        public Builder withMethodPayment(MethodPayment methodPayment) {
            instance.methodPayment = methodPayment;
            return this;
        }

        public Builder withFundingInstrument(FundingInstrument fundingInstrument) {
            instance.fundingInstrument = fundingInstrument;
            return this;
        }

        public Builder withEcommerceOrder(EcommerceOrder ecommerceOrder) {
            instance.ecommerceOrder = Optional.of(ecommerceOrder);
            return this;
        }

        public PaymentDefault build() {
            return instance;
        }
    }

}
