package com.formento.ecommerce.productPrice.model.template;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.formento.ecommerce.product.model.Product;
import com.formento.ecommerce.productPrice.model.ProductPriceEntity;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ProductPriceTemplate implements TemplateLoader {

    public static String VALID_CURRENT_PRODUCT_PRICE = "validCurrentProductPrice";
    public static String VALID_TABLE_CURRENT_PRODUCT_PRICE = "validTableCurrentProductPrice";
    public static String VALID_PRODUCT_PRICE_FROM_ONE_MONTH_AGO = "validProductPriceFromOneMonthAgo";
    public static String VALID_PRODUCT_PRICE_TO_NEXT_MONTH = "validProductPriceToNextMonth";

    @Override
    public void load() {
        Fixture.of(ProductPriceEntity.class)
                .addTemplate(VALID_CURRENT_PRODUCT_PRICE, new Rule() {{
                    add("id", 10l);
                    add("initialDate", LocalDate.now());
                    add("product", new Product(1l, null, null, null, null));
                    add("price", BigDecimal.ONE);
                }})
                .addTemplate(VALID_PRODUCT_PRICE_FROM_ONE_MONTH_AGO, new Rule() {{
                    add("id", 11l);
                    add("initialDate", LocalDate.now().minusMonths(1));
                    add("product", new Product(1l, null, null, null, null));
                    add("price", BigDecimal.TEN);
                }})
                .addTemplate(VALID_PRODUCT_PRICE_TO_NEXT_MONTH, new Rule() {{
                    add("id", 12l);
                    add("initialDate", LocalDate.now().plusMonths(1));
                    add("product", new Product(1l, null, null, null, null));
                    add("price", BigDecimal.valueOf(15l));
                }})
                .addTemplate(VALID_TABLE_CURRENT_PRODUCT_PRICE, new Rule() {{
                    add("id", 13l);
                    add("initialDate", LocalDate.now());
                    add("product", new Product(2l, null, null, null, null));
                    add("price", BigDecimal.TEN);
                }});
    }

}
