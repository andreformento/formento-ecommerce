package com.formento.ecommerce.discount.model;

import com.formento.ecommerce.exception.DiscountEcommerceException;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Coupon implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Size(min = 1, max = 80)
    private String code;

    @NotNull
    private BigDecimal percent;

    private LocalDate expirationDate;

    // any logical to validate coupon
    public void validate(LocalDate date) {
        if (expirationDate.isBefore(date)) {
            throw new DiscountEcommerceException("discount.coupon.invalid.expiredDate");
        }
    }

    public void validateNow() {
        validate(LocalDate.now());
    }

}
