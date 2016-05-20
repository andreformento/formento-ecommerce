package com.formento.ecommerce.product.converter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.formento.ecommerce.product.model.Product;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ProductListSerializer extends JsonSerializer<Collection<Product>> {

    @Override
    public void serialize(Collection<Product> value, JsonGenerator jsonGenerator, SerializerProvider provider) throws IOException {
        List<Product> products = value
                .stream()
                .map(product -> new Product.Builder()
                        .withId(product.getId())
                        .withName(product.getName())
                        .withDescription(product.getDescription())
                        .withAvailability(product.getAvailability())
                        .build())
                .collect(Collectors.toList());

        jsonGenerator.writeObject(products);
    }

}
