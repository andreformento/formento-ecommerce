package com.formento.ecommerce.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
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

}
