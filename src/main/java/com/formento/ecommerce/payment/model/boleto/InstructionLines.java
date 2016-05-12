package com.formento.ecommerce.payment.model.boleto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class InstructionLines {

    private String first;
    private String second;
    private String third;

}
