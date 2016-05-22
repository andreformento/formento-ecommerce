package com.formento.ecommerce.ecommerceOrder.model.converter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.formento.ecommerce.ecommerceOrder.model.ItemOrder;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ItemOrdersSerializer extends JsonSerializer<Collection<ItemOrder>> {

    @Override
    public void serialize(Collection<ItemOrder> value, JsonGenerator jsonGenerator, SerializerProvider provider) throws IOException {
        List<ItemOrder> itemOrders = value
                .stream()
                .map(itemOrder -> new ItemOrder
                        .Builder()
                        .withId(itemOrder.getId())
                        .withProduct(itemOrder.getProduct())
                        .withTotalPrice(itemOrder.getTotalPrice())
                        .withQuantity(itemOrder.getQuantity())
                        .withUnitPrice(itemOrder.getUnitPrice())
                        .build())
                .collect(Collectors.toList());

        jsonGenerator.writeObject(itemOrders);
    }

}
