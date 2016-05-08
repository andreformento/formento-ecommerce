package com.formento.ecommerce.productPrice.converter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.formento.ecommerce.productPrice.model.ProductPriceEntity;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Optional;

public class ProductPriceSerializer extends JsonSerializer<Optional<ProductPriceEntity>> {

    @Override
    public void serialize(Optional<ProductPriceEntity> value, JsonGenerator jsonGenerator, SerializerProvider provider) throws IOException {
        jsonGenerator.writeObject(value.map(ProductPriceEntity::getTotalPrice).orElse(BigDecimal.ZERO));
    }

}