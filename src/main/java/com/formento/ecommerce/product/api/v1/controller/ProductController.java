package com.formento.ecommerce.product.api.v1.controller;

import com.formento.ecommerce.product.model.Product;
import com.formento.ecommerce.product.service.ProductService;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
@RequestMapping("/api/v1/products")
@Validated
@AllArgsConstructor
@NoArgsConstructor
@Api(value = "API of product", description = "Product", basePath = "/api/v1/products", produces = "application/json")
public class ProductController {

    @Autowired
    private ProductService productService;

    @ApiOperation(value = "Get all avaliable products", notes = "Get all avaliable products", response = Product.class)
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<Resources<Product>> getAllFromLoggedUser() {
        return new ResponseEntity<>(new Resources<>(productService.findByAvaliable(), linkTo(ProductController.class).withSelfRel()), HttpStatus.CREATED);
    }

}
