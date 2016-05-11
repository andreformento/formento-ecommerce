package com.formento.ecommerce.util.converter;

import com.formento.ecommerce.util.LocalDateUtil;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.LocalDate;
import java.util.Date;

@Converter(autoApply = true)
public class LocalDateConverter implements AttributeConverter<LocalDate, Date> {

    @Override
    public Date convertToDatabaseColumn(LocalDate date) {
//        Instant instant = Instant.from(date);
//        return Date.from(instant);
        return LocalDateUtil.toDate(date);
    }

    @Override
    public LocalDate convertToEntityAttribute(Date value) {
//        Instant instant = value.toInstant();
//        return LocalDate.from(instant);
        return LocalDateUtil.asLocalDate(value);
    }
}
