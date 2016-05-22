package com.formento.ecommerce.payment.model;

import com.formento.ecommerce.installment.model.Installment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Embeddable
public class InstallmentEntity implements Installment, Serializable {

    private BigDecimal totalValue;

    public InstallmentEntity(Installment installment) {
        this.totalValue = installment.getTotalValue();
    }
}
