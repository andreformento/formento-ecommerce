package com.formento.ecommerce.payment.api.v1.controller;

import com.formento.ecommerce.payment.model.Payment;
import com.formento.ecommerce.payment.service.BoletoPaymentService;
import com.formento.ecommerce.payment.service.request.BoletoPaymentRequest;
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
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Integration API to Boleto payment
 */

@RestController
@RequestMapping("/api/v1/boleto-payment-payments")
@Validated
@AllArgsConstructor
@NoArgsConstructor
@Api(value = "API of boleto payment", produces = "application/json")
class BoletoPaymentController {

    @Autowired
    private BoletoPaymentService boletoPaymentService;

    /**
     * Resource to order
     *
     * @param orderId              id of order
     * @param boletoPaymentRequest params to do request
     * @return Payment resource
     */
    @ApiOperation(value = "Create a payment for an order", notes = "Create a payment for an order and return a payment", response = Payment.class)
    @RequestMapping(value = "/order/{orderId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<Resource<Payment>> createPayment(@PathVariable("orderId") Long orderId, @RequestBody BoletoPaymentRequest boletoPaymentRequest) {
        return new ResponseEntity<>(new Resource<>(boletoPaymentService.createPayment(orderId, boletoPaymentRequest), linkTo(BoletoPaymentController.class).withSelfRel()), HttpStatus.CREATED);
    }

}
