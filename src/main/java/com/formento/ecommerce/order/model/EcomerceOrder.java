package com.formento.ecommerce.order.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.formento.ecommerce.order.model.converter.ItemOrdersSerializer;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class EcomerceOrder implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @JsonSerialize(using = ItemOrdersSerializer.class)
    @OneToMany(mappedBy = "ecomerceOrder")
    private Collection<ItemOrder> itemOrders;

}
