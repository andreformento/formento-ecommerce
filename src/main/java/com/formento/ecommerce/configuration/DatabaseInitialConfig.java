package com.formento.ecommerce.configuration;

import com.formento.ecommerce.discount.model.Coupon;
import com.formento.ecommerce.discount.repository.CouponRepository;
import com.formento.ecommerce.product.model.Product;
import com.formento.ecommerce.product.repository.ProductRepository;
import com.formento.ecommerce.productPrice.model.ProductPriceEntity;
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
        Product chair = productRepository.save(new Product.Builder()
                .withName("Chair")
                .withDescription("Beautiful chair")
                .withAvailability(5)
                .build()
        );

        ProductPriceEntity.Builder builderChairPrice = new ProductPriceEntity.Builder().withProduct(chair);

        productPriceRepository.save(builderChairPrice
                .withPrice(BigDecimal.valueOf(5))
                .withInitialDate(LocalDate.now().minusMonths(1))
                .build());
        productPriceRepository.save(builderChairPrice
                .withPrice(BigDecimal.valueOf(10))
                .withInitialDate(LocalDate.now())
                .build());
        productPriceRepository.save(builderChairPrice
                .withPrice(BigDecimal.valueOf(15))
                .withInitialDate(LocalDate.now().plusMonths(1))
                .build());

        couponRepository.save(new Coupon(null, "funny-promo", BigDecimal.valueOf(5), LocalDate.now().plusYears(10l)));
        couponRepository.save(new Coupon(null, "boring-promo", BigDecimal.valueOf(15), LocalDate.now().minusDays(3)));
    }

}
