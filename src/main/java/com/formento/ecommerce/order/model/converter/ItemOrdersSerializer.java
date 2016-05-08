package com.formento.ecommerce.order.model.converter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.formento.ecommerce.order.model.ItemOrder;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ItemOrdersSerializer extends JsonSerializer<Collection<ItemOrder>> {

    @Override
    public void serialize(Collection<ItemOrder> value, JsonGenerator jsonGenerator, SerializerProvider provider) throws IOException {
        List<ItemOrder> itemOrders = value
                .stream()
                .map(ItemOrder::new)
                .collect(Collectors.toList());

        jsonGenerator.writeObject(itemOrders);
    }

}
