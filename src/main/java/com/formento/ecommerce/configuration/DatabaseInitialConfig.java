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
                        .withUrlImage("http://www.belesyfurniture.com/image/cache/data/8503-C/19-600x350.jpg")
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
                        .withUrlImage("http://safarimp.com/wp-content/uploads/2016/02/brilliant-new-office-reception-furniture-new-office-furniture-reception-with-glass-office-table.jpg")
                        .withAvailability(15)
                        .addProductPrice(BigDecimal.valueOf(250), LocalDate.now().minusMonths(1))
                        .build()
                ).getProductPrices());

        productPriceRepository.save(
                productRepository.save(new Product.Builder()
                        .withName("Computador")
                        .withDescription("Apple")
                        .withUrlImage("http://technozigzag.com/wp-content/uploads/2014/10/apple-imac-retina-display.jpg")
                        .withAvailability(30)
                        .addProductPrice(BigDecimal.valueOf(2500), LocalDate.now().minusMonths(1))
                        .build()
                ).getProductPrices());

        productPriceRepository.save(
                productRepository.save(new Product.Builder()
                        .withName("Kit para escritório")
                        .withDescription("Kit contendo vários itens para escritório")
                        .withUrlImage("http://conasia.net/product_pic/office%20accessories.jpg")
                        .withAvailability(5000)
                        .addProductPrice(BigDecimal.valueOf(129.99), LocalDate.now().minusDays(10))
                        .build()
                ).getProductPrices());

        couponRepository.save(new Coupon(null, "funny-promo", BigDecimal.valueOf(5), LocalDate.now().plusYears(10l)));
        couponRepository.save(new Coupon(null, "boring-promo", BigDecimal.valueOf(15), LocalDate.now().minusDays(3)));
    }

}
