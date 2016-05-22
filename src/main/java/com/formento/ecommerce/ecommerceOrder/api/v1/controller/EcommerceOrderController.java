package com.formento.ecommerce.ecommerceOrder.api.v1.controller;

import com.formento.ecommerce.ecommerceOrder.model.EcommerceOrder;
import com.formento.ecommerce.ecommerceOrder.service.EcommerceOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
@RequestMapping("/api/v1/orders")
@Validated
@AllArgsConstructor
@NoArgsConstructor
@Api(value = "API of order", description = "Order", basePath = "/api/v1/orders", produces = "application/json")
public class EcommerceOrderController {

    @Autowired
    private EcommerceOrderService ecommerceOrderService;

    @ApiOperation(value = "Create an order", notes = "Create and return an order", response = EcommerceOrder.class)
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<Resource<EcommerceOrder>> create() {
        Resource<EcommerceOrder> resource = new Resource<>(ecommerceOrderService.createIntegration(), linkTo(EcommerceOrderController.class).withSelfRel());
        return new ResponseEntity<>(resource, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Get an order", notes = "Get and return an order", response = EcommerceOrder.class)
    @RequestMapping(value = "/{orderId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<Resource<EcommerceOrder>> getOrderById(@PathVariable("orderId") Long orderId) {
        return new ResponseEntity<>(new Resource<>(ecommerceOrderService.getValidatedOrderById(orderId), linkTo(EcommerceOrderController.class).withSelfRel()), HttpStatus.CREATED);
    }

}
