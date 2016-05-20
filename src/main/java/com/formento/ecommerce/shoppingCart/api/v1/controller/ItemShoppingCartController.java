package com.formento.ecommerce.shoppingCart.api.v1.controller;

import com.formento.ecommerce.shoppingCart.model.ItemShoppingCart;
import com.formento.ecommerce.shoppingCart.model.ShoppingCart;
import com.formento.ecommerce.shoppingCart.service.ItemShoppingCartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
@RequestMapping("/api/v1/item-shopping-carts")
@Validated
@AllArgsConstructor
@NoArgsConstructor
@Api(value = "API of item shopping cart", description = "Item shopping cart", basePath = "/api/v1/item-shopping-carts", produces = "application/json")
public class ItemShoppingCartController {

    @Autowired
    private ItemShoppingCartService itemShoppingCartService;

    @ApiOperation(value = "Add a item shopping cart", notes = "Add a item in a shopping cart and return updated list from shopping cart", response = ShoppingCart.class)
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<Resources<ItemShoppingCart>> addItemShoppingCart(@RequestBody ItemShoppingCart itemShoppingCart) {
        return new ResponseEntity<>(new Resources<>(itemShoppingCartService.addItemShoppingCart(itemShoppingCart), linkTo(ItemShoppingCartController.class).withSelfRel()), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Add a item shopping cart", notes = "Add a item in a shopping cart and return updated list from shopping cart", response = ShoppingCart.class)
    @RequestMapping(value = "/{itemShoppingCartId}",method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<Resources<ItemShoppingCart>> removeItemShoppingCart(@PathVariable("itemShoppingCartId") Long itemShoppingCartId) {
        itemShoppingCartService.removeItemShoppingCart(itemShoppingCartId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "Get all items of shopping cart from logged user", notes = "Get all items of shopping cart from logged user", response = ItemShoppingCart.class)
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<Resources<ItemShoppingCart>> getAllFromLoggedUser() {
        return new ResponseEntity<>(new Resources<>(itemShoppingCartService.getAllFromLoggedUser(), linkTo(ItemShoppingCartController.class).withSelfRel()), HttpStatus.OK);
    }

}
