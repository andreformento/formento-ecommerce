package com.formento.ecommerce.payment.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.formento.ecommerce.ecommerceOrder.model.EcommerceOrder;
import com.formento.ecommerce.util.converter.OptionalObjectSerializer;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Optional;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
public final class PaymentIntegration implements Payment {
    private MethodPayment methodPayment;
    private FundingInstrument fundingInstrument;

    @JsonSerialize(using = OptionalObjectSerializer.class)
    private Optional<EcommerceOrder> ecommerceOrder;

    @JsonSerialize(using = OptionalObjectSerializer.class)
    private Optional<String> integrationId;

    public PaymentIntegration() {
        ecommerceOrder = Optional.empty();
    }

    public static class Builder {
        private PaymentIntegration instance = new PaymentIntegration();

        public Builder withMethodPayment(MethodPayment methodPayment) {
            instance.methodPayment = methodPayment;
            return this;
        }

        public Builder withFundingInstrument(FundingInstrument fundingInstrument) {
            instance.fundingInstrument = fundingInstrument;
            return this;
        }

        public Builder withIntegrationId(String integrationId) {
            instance.integrationId = Optional.of(integrationId);
            return this;
        }

        public Builder withEcommerceOrder(EcommerceOrder ecommerceOrder) {
            instance.ecommerceOrder = Optional.of(ecommerceOrder);
            return this;
        }

        public PaymentIntegration build() {
            return instance;
        }
    }

}
