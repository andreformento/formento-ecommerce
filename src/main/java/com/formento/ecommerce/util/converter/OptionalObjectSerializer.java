package com.formento.ecommerce.util.converter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.Optional;

public class OptionalObjectSerializer extends JsonSerializer<Optional<Object>> {

    @Override
    public void serialize(Optional<Object> value, JsonGenerator jsonGenerator, SerializerProvider provider) throws IOException {
        if (value != null && value.isPresent()) {
            jsonGenerator.writeObject(value.get());
        }
    }

}