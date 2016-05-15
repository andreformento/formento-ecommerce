package com.formento.ecommerce.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.formento.ecommerce.security.UserAuthentication;
import com.formento.ecommerce.util.converter.EmptyFieldSerializer;
import com.formento.ecommerce.util.converter.LocalDateSerializer;
import com.formento.ecommerce.util.converter.LocalDateTimeSerializer;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
// Immutable
public class User implements UserAuthentication, Serializable {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    @JsonIgnore
    @Getter(AccessLevel.NONE)
    private UUID uuid;

    @Size(min = 5, max = 80)
    private String name;

    @Email
    @Size(min = 5, max = 80)
    @Column(unique = true)
    private String email;

    @NotEmpty
    @JsonSerialize(using = EmptyFieldSerializer.class)
    private String password;

    private String token;

    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate creationDate;

    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate updateDate;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime lastLogin;

    public static class Builder {
        private User instance = new User();

        public Builder withSelf(User user) {
            instance.uuid = user.uuid;
            instance.name = user.name;
            instance.email = user.email;
            instance.password = user.password;
            instance.token = user.token;
            instance.creationDate = user.creationDate;
            instance.updateDate = user.updateDate;
            instance.lastLogin = user.lastLogin;
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

        public Builder withCreationDate(LocalDate creationDate) {
            instance.creationDate = creationDate;
            return this;
        }

        public Builder withUpdateDate(LocalDate updateDate) {
            instance.updateDate = updateDate;
            return this;
        }

        public Builder withLastLogin(LocalDateTime lastLogin) {
            instance.lastLogin = lastLogin;
            return this;
        }

        public User build() {
            return instance;
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return uuid != null ? uuid.equals(user.uuid) : user.uuid == null;

    }

    @Override
    public int hashCode() {
        return uuid != null ? uuid.hashCode() : 0;
    }

    @JsonIgnore
    public UUID getId() {
        return uuid;
    }

}
