package com.formento.ecommerce.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TaxDocument implements Serializable {

    @Id
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;

    @Enumerated(value = EnumType.STRING)
    private Type type;

    @NotEmpty
    private String number;

    public enum Type {
        CPF, CNPJ
    }

}
