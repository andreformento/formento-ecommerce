package com.formento.ecommerce.payment.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class MethodPaymentEntity implements MethodPayment, Serializable {

    @Id
    @GeneratedValue
    @JsonProperty
    private Long id;

    private BigDecimal totalValue;

    @Column(name = "count_value")
    private Integer count;

    @ElementCollection
    private Collection<InstallmentEntity> installments;

    public MethodPaymentEntity(MethodPayment methodPayment) {
        this.totalValue = methodPayment.getTotalValue();
        this.count = methodPayment.getCount();
        this.installments = methodPayment
                .getInstallments()
                .stream()
                .map(InstallmentEntity::new)
                .collect(Collectors.toList());
    }
}
