package com.formento.ecommerce.util.converter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.formento.ecommerce.util.LocalDateTimeUtil;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

public class OptionalLocalDateTimeSerializer extends JsonSerializer<Optional<LocalDateTime>> {

    @Override
    public void serialize(Optional<LocalDateTime> value, JsonGenerator jsonGenerator, SerializerProvider provider) throws IOException {
        if (value != null && value.isPresent()) {
            jsonGenerator.writeObject(LocalDateTimeUtil.format(value.get()));
        }
    }

}