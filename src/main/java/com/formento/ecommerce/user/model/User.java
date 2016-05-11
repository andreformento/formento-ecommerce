package com.formento.ecommerce.user.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
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

    public static class Builder {
        private User instance;

        public Builder() {
            this.instance = new User();
        }

        public Builder withId(Long id) {
            instance.id = id;
            return this;
        }

        public Builder withName(String name) {
            instance.name = name;
            return this;
        }

        public Builder withEmail(String email) {
            instance.email = email;
            return this;
        }

        public Builder withPassword(String password) {
            instance.password = password;
            return this;
        }

        public Builder withToken(String token) {
            instance.token = token;
            return this;
        }

        public User build() {
            return instance;
        }
    }

}
