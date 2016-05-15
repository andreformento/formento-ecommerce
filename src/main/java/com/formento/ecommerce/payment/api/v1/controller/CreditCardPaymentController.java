package com.formento.ecommerce.payment.api.v1.controller;

import com.formento.ecommerce.payment.model.Payment;
import com.formento.ecommerce.payment.model.creditCard.CreditCardEcommerceFundingInstrument;
import com.formento.ecommerce.payment.service.CreditCardPaymentService;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
@RequestMapping("/api/v1/credit-card-payment")
@Validated
@AllArgsConstructor
@NoArgsConstructor
@Api(value = "API of credit card payment", description = "Credit card payment", basePath = "/api/v1/credit-card-payment", produces = "application/json")
public class CreditCardPaymentController {

    @Autowired
    private CreditCardPaymentService creditCardPaymentService;

    @ApiOperation(value = "Create a payment for an order", notes = "Create a payment for an order and return a payment", response = Payment.class)
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<Resource<Payment>> addItem(BigDecimal totalValue, Integer quantity, CreditCardEcommerceFundingInstrument fundingInstrument) {
        return new ResponseEntity<>(new Resource<>(creditCardPaymentService.createPayment(totalValue, quantity, fundingInstrument), linkTo(CreditCardPaymentController.class).withSelfRel()), HttpStatus.CREATED);
    }

}
