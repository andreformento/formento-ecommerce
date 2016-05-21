package com.formento.ecommerce;

import com.formento.ecommerce.discount.model.Coupon;
import com.formento.ecommerce.discount.repository.CouponRepository;
import com.formento.ecommerce.product.model.Product;
import com.formento.ecommerce.product.repository.ProductRepository;
import com.formento.ecommerce.productPrice.model.ProductPrice;
import com.formento.ecommerce.productPrice.model.ProductPriceEntity;
import com.formento.ecommerce.productPrice.repository.ProductPriceRepository;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = FormentoEcommerceApplication.class)
public class FormentoEcommerceApplicationTests {

    @Autowired
    protected ProductRepository productRepository;

    @Autowired
    protected ProductPriceRepository productPriceRepository;

    @Autowired
    protected CouponRepository couponRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Before
    public void init() {
        when(authenticationManager.authenticate(any())).thenReturn(new AnonymousAuthenticationToken("", null, ImmutableList.of(new SimpleGrantedAuthority(""))));
//        when(SecurityContextHolder.getContext().setAuthentication(any()));
    }

    @Test
    public void contextLoads() {
    }

    @Test
    public void shouldCreateInitialProducts() {
        // given
        Iterable<Product> all = productRepository.findAll();

        // when
        ArrayList<Product> products = Lists.newArrayList(all);

        // then
        assertNotNull(products);
        assertFalse(products.isEmpty());
    }

    @Test
    public void shouldCreateInitialProductPrices() {
        // given
        Iterable<ProductPriceEntity> all = productPriceRepository.findAll();

        // when
        ArrayList<ProductPrice> productPrices = Lists.newArrayList(all);

        // then
        assertNotNull(productPrices);
        assertFalse(productPrices.isEmpty());
    }

    @Test
    public void shouldCreateInitialCoupons() {
        // given
        Iterable<Coupon> all = couponRepository.findAll();

        // when
        ArrayList<Coupon> coupons = Lists.newArrayList(all);

        // then
        assertNotNull(coupons);
        assertFalse(coupons.isEmpty());
    }

}
