package com.formento.ecommerce.discount.model;

import com.formento.ecommerce.exception.DiscountEcommerceException;
import lombok.*;

import javax.persistence.Column;
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
@ToString
public class Coupon implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Column(unique = true)
    @Size(min = 1, max = 80)
    private String code;

    @NotNull
    private BigDecimal percent;

    private LocalDate expirationDate;

    // any logical to validate coupon
    public Coupon validate(LocalDate date) {
        if (expirationDate.isBefore(date)) {
            throw new DiscountEcommerceException("discount.coupon.invalid.expiredDate");
        }
        return this;
    }

    public Coupon validateNow() {
        return validate(LocalDate.now());
    }

}
