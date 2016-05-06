package com.formento.ecommerce.productPrice.converter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.formento.ecommerce.productPrice.model.ProductPriceDefault;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ProductPricesSerializer extends JsonSerializer<Collection<ProductPriceDefault>> {

    @Override
    public void serialize(Collection<ProductPriceDefault> value, JsonGenerator jsonGenerator, SerializerProvider provider) throws IOException {
        List<ProductPriceDefault> productPrices = value
                .stream()
                .map(productPrice -> new ProductPriceDefault.Builder()
                        .withPrice(productPrice.getPrice())
                        .withInitialDate(productPrice.getInitialDate())
                        .build())
                .collect(Collectors.toList());

        jsonGenerator.writeObject(productPrices);
    }

}
