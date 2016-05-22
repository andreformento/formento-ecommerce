package com.formento.ecommerce.payment.api.v1.controller;

import com.formento.ecommerce.payment.model.Payment;
import com.formento.ecommerce.payment.service.PaymentEditService;
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
@RequestMapping("/api/v1/payments")
@Validated
@AllArgsConstructor
@NoArgsConstructor
@Api(value = "API of payment", description = "API of payment", basePath = "/api/v1/payments", produces = "application/json")
public class PaymentController {

    @Autowired
    private PaymentEditService paymentEditService;

    @ApiOperation(value = "Create a payment for an order", notes = "Create a payment for an order and return a payment", response = Payment.class)
    @RequestMapping(value = "/{paymentId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<Resource<Payment>> createPayment(@PathVariable("paymentId") Long paymentId) {
        return new ResponseEntity<>(new Resource<>(paymentEditService.getPaymentById(paymentId), linkTo(PaymentController.class).withSelfRel()), HttpStatus.CREATED);
    }

}
