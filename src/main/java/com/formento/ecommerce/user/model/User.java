package com.formento.ecommerce.user.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
// Immutable
public class User implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Size(min = 5, max = 80)
    private String name;

    @Email
    @Size(min = 5, max = 80)
    @Column(unique = true)
    private String email;

    @NotEmpty
    private String password;

    private String token;

    private LocalDate birthDate;

    @OneToOne(fetch = FetchType.LAZY)
    private TaxDocument taxDocument;

}
