package com.formento.ecommerce.util.converter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.formento.ecommerce.util.LocalDateUtil;

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