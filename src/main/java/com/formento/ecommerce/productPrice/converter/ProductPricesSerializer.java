package com.formento.ecommerce.productPrice.converter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.formento.ecommerce.productPrice.model.ProductPrice;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ProductPricesSerializer extends JsonSerializer<Collection<ProductPrice>> {

    @Override
    public void serialize(Collection<ProductPrice> value, JsonGenerator jsonGenerator, SerializerProvider provider) throws IOException {
        List<ProductPrice> productPrices = value
                .stream()
                .map(productPrice -> new ProductPrice.Builder()
                        .withPrice(productPrice.getPrice())
                        .withInitialDate(productPrice.getInitialDate())
                        .build())
                .collect(Collectors.toList());

        jsonGenerator.writeObject(productPrices);
    }

}
