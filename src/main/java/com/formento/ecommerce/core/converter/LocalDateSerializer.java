package com.formento.ecommerce.core.converter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.formento.ecommerce.core.util.LocalDateUtil;

import java.io.IOException;
import java.time.LocalDate;

public class LocalDateSerializer extends JsonSerializer<LocalDate> {

    @Override
    public void serialize(LocalDate value, JsonGenerator jsonGenerator, SerializerProvider provider) throws IOException {
        if (value != null) {
            jsonGenerator.writeObject(LocalDateUtil.format(value));
        }
    }

}