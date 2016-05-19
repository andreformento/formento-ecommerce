package com.formento.ecommerce.product.model.template;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.formento.ecommerce.product.model.Product;
import com.formento.ecommerce.productPrice.model.ProductPriceEntity;
import com.formento.ecommerce.productPrice.model.template.ProductPriceTemplate;

import java.util.ArrayList;

public class ProductTemplate implements TemplateLoader {

    public static String VALID_WITH_ID = "validWithId";
    public static String VALID_WITH_PRODUCT_PRICES = "validWithProductPrices";

    @Override
    public void load() {
        Fixture.of(Product.class)
                .addTemplate(VALID_WITH_ID, new Rule() {{
                    add("id", 2l);
                    add("name", "Chair");
                    add("description", "Beautiful chair");
                    add("urlImage", "http://www.designwreck.com/wp-content/uploads/2014/04/tiles-chair-03.jpg");
                    add("availability", 5);
                    add("productPrices", new ArrayList<>());
                }})
                .addTemplate(VALID_WITH_PRODUCT_PRICES)
                .inherits(VALID_WITH_ID, new Rule() {{
                    add("productPrices", has(3).of(ProductPriceEntity.class,
                            ProductPriceTemplate.VALID_CURRENT_PRODUCT_PRICE,
                            ProductPriceTemplate.VALID_PRODUCT_PRICE_FROM_ONE_MONTH_AGO,
                            ProductPriceTemplate.VALID_PRODUCT_PRICE_TO_NEXT_MONTH
                    ));
                }});
    }

}
