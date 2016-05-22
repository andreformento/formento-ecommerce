package com.formento.ecommerce.payment.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.formento.ecommerce.ecommerceOrder.model.EcommerceOrder;
import com.formento.ecommerce.util.converter.OptionalObjectSerializer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class PaymentEntity implements Payment, Serializable {

    @Id
    @GeneratedValue
    @JsonProperty
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    private MethodPaymentEntity methodPayment;

    @ManyToOne(cascade = CascadeType.ALL)
    private FundingInstrumentEntity fundingInstrument;

    private String integrationId;

    @ManyToOne
    private EcommerceOrder ecommerceOrder;

    @JsonSerialize(using = OptionalObjectSerializer.class)
    public Optional<String> getIntegrationId() {
        return Optional.ofNullable(this.integrationId);
    }

    @JsonSerialize(using = OptionalObjectSerializer.class)
    public Optional<EcommerceOrder> getEcommerceOrder() {
        return Optional.ofNullable(this.ecommerceOrder);
    }

    public EcommerceOrder getPaymentEcommerceOrder() {
        return this.ecommerceOrder;
    }

    public Long getPaymentId() {
        return this.id;
    }

    public PaymentEntity(Payment payment) {
        this.methodPayment = new MethodPaymentEntity(payment.getMethodPayment());
        this.fundingInstrument = new FundingInstrumentEntity(payment.getFundingInstrument());

        payment.getIntegrationId().ifPresent(integrationId -> this.integrationId = integrationId);
        payment.getEcommerceOrder().ifPresent(ecommerceOrder -> this.ecommerceOrder = ecommerceOrder);
    }

}
