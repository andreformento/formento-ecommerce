package com.formento.ecommerce.shoppingCart.api.v1.controller;

import com.formento.ecommerce.shoppingCart.model.ShoppingCart;
import com.formento.ecommerce.shoppingCart.service.ShoppingCartService;
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

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
@RequestMapping("/api/v1/shopping-carts")
@Validated
@AllArgsConstructor
@NoArgsConstructor
@Api(value = "API of shopping cart", description = "Shopping cart", basePath = "/api/v1/shopping-carts", produces = "application/json")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @ApiOperation(value = "Get shopping cart from logged user", notes = "Get shopping cart from logged user", response = ShoppingCart.class)
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<Resource<ShoppingCart>> getAllFromLoggedUser() {
        return new ResponseEntity<>(new Resource<>(shoppingCartService.getOrCreateCurrentFromUser(), linkTo(ShoppingCartController.class).withSelfRel()), HttpStatus.OK);
    }

}
