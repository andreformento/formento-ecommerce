package com.formento.ecommerce.productPrice.converter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.formento.ecommerce.productPrice.model.ProductPriceEntity;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ProductPricesSerializer extends JsonSerializer<Collection<ProductPriceEntity>> {

    @Override
    public void serialize(Collection<ProductPriceEntity> value, JsonGenerator jsonGenerator, SerializerProvider provider) throws IOException {
        List<ProductPriceEntity> productPrices = value
                .stream()
                .map(productPrice -> new ProductPriceEntity.Builder()
                        .withPrice(productPrice.getTotalPrice())
                        .withInitialDate(productPrice.getInitialDate())
                        .build())
                .collect(Collectors.toList());

        jsonGenerator.writeObject(productPrices);
    }

}
