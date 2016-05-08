package com.formento.ecommerce.configuration;

import com.formento.ecommerce.discount.model.Coupon;
import com.formento.ecommerce.discount.repository.CouponRepository;
import com.formento.ecommerce.product.model.Product;
import com.formento.ecommerce.product.repository.ProductRepository;
import com.formento.ecommerce.productPrice.repository.ProductPriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.LocalDate;

@Component
@Scope("singleton")
public class DatabaseInitialConfig {

    private final ProductRepository productRepository;
    private final ProductPriceRepository productPriceRepository;
    private final CouponRepository couponRepository;

    @Autowired
    public DatabaseInitialConfig(ProductRepository productRepository, ProductPriceRepository productPriceRepository, CouponRepository couponRepository) {
        this.productRepository = productRepository;
        this.productPriceRepository = productPriceRepository;
        this.couponRepository = couponRepository;
    }

    @PostConstruct
    public void initializeValues() {
        productPriceRepository.save(
                productRepository.save(new Product.Builder()
                        .withName("Cadeira")
                        .withDescription("Cadeira com rodinhas")
                        .withAvailability(5)
                        .addProductPrice(BigDecimal.valueOf(50), LocalDate.now().minusMonths(1))
                        .addProductPrice(BigDecimal.valueOf(120), LocalDate.now())
                        .addProductPrice(BigDecimal.valueOf(200), LocalDate.now().plusMonths(6))
                        .build()
                ).getProductPrices());

        productPriceRepository.save(
                productRepository.save(new Product.Builder()
                        .withName("Mesa")
                        .withDescription("Mesa de escritório")
                        .withAvailability(15)
                        .addProductPrice(BigDecimal.valueOf(250), LocalDate.now().minusMonths(1))
                        .build()
                ).getProductPrices());

        productPriceRepository.save(
                productRepository.save(new Product.Builder()
                        .withName("Computador")
                        .withDescription("Computador com Linux")
                        .withAvailability(30)
                        .addProductPrice(BigDecimal.valueOf(2500), LocalDate.now().minusMonths(1))
                        .build()
                ).getProductPrices());

        productPriceRepository.save(
                productRepository.save(new Product.Builder()
                        .withName("Folhas")
                        .withDescription("Bloco de 500 folhas")
                        .withAvailability(5000)
                        .addProductPrice(BigDecimal.valueOf(13), LocalDate.now().minusDays(10))
                        .build()
                ).getProductPrices());

        couponRepository.save(new Coupon(null, "funny-promo", BigDecimal.valueOf(5), LocalDate.now().plusYears(10l)));
        couponRepository.save(new Coupon(null, "boring-promo", BigDecimal.valueOf(15), LocalDate.now().minusDays(3)));
    }

}
