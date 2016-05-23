package com.formento.ecommerce.payment.model.creditCard;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.formento.ecommerce.util.converter.LocalDateDeserializer;
import com.formento.ecommerce.util.converter.LocalDateSerializer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreditCardHolder {

    @Size(min = 5, max = 80)
    private String name;

    @NotNull
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate birthdate;

    @NotNull
    private TaxDocument taxDocument;

    private Phone phone;

}
