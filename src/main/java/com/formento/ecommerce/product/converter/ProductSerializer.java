package com.formento.ecommerce.product.converter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.formento.ecommerce.productPrice.model.ProductPriceEntity;

import java.io.IOException;

public class ProductSerializer extends JsonSerializer<ProductPriceEntity> {

    @Override
    public void serialize(ProductPriceEntity value, JsonGenerator jsonGenerator, SerializerProvider provider) throws IOException {
        if (value != null)
            jsonGenerator.writeObject(value);
    }

}