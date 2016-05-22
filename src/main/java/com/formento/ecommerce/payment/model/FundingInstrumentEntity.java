package com.formento.ecommerce.payment.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class FundingInstrumentEntity implements FundingInstrument, Serializable {

    @Id
    @GeneratedValue
    @JsonProperty
    private Long id;

    private String method;

    public FundingInstrumentEntity(FundingInstrument fundingInstrument) {
        this.method = fundingInstrument.getMethod();
    }

}
