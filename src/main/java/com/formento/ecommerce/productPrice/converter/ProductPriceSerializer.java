package com.formento.ecommerce.productPrice.converter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.formento.ecommerce.productPrice.model.ProductPriceDefault;

import java.io.IOException;
import java.util.Optional;

public class ProductPriceSerializer extends JsonSerializer<Optional<ProductPriceDefault>> {

    @Override
    public void serialize(Optional<ProductPriceDefault> value, JsonGenerator jsonGenerator, SerializerProvider provider) throws IOException {
        if (value.isPresent()) {
            jsonGenerator.writeObject(new ProductPriceDefault.Builder()
                    .withInitialDate(value.get().getInitialDate())
                    .withPrice(value.get().getPrice())
                    .build()
            );
        }
    }

}