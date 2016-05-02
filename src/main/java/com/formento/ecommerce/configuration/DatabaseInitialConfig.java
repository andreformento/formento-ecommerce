package com.formento.ecommerce.configuration;

import com.formento.ecommerce.product.model.Product;
import com.formento.ecommerce.product.repository.ProductRepository;
import com.formento.ecommerce.productPrice.model.ProductPrice;
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

    @Autowired
    public DatabaseInitialConfig(ProductRepository productRepository, ProductPriceRepository productPriceRepository) {
        this.productRepository = productRepository;
        this.productPriceRepository = productPriceRepository;
    }

    @PostConstruct
    public void initializeValues() {
        Product chair = productRepository.save(new Product.Builder()
                .withName("Chair")
                .withDescription("Beautiful chair")
                .withAvailability(5)
                .build()
        );

        ProductPrice.Builder builderChairPrice = new ProductPrice.Builder().withProduct(chair);

        ProductPrice chairPriceOneMonthAgo = productPriceRepository.save(builderChairPrice.withPrice(BigDecimal.valueOf(5)).withInitialDate(LocalDate.now().minusMonths(1)).build());
        ProductPrice chairCurrentPrice = productPriceRepository.save(builderChairPrice.withPrice(BigDecimal.valueOf(10)).withInitialDate(LocalDate.now()).build());
        ProductPrice chairPriceNextMonth = productPriceRepository.save(builderChairPrice.withPrice(BigDecimal.valueOf(15)).withInitialDate(LocalDate.now().plusMonths(1)).build());

        System.out.println(chairPriceOneMonthAgo);
        System.out.println(chairCurrentPrice);
        System.out.println(chairPriceNextMonth);
    }

}
