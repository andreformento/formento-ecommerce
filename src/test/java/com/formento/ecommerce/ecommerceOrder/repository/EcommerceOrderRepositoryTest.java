package com.formento.ecommerce.ecommerceOrder.repository;

import com.formento.ecommerce.FormentoEcommerceApplication;
import com.formento.ecommerce.ecommerceOrder.model.EcommerceOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = FormentoEcommerceApplication.class)
@IntegrationTest
public class EcommerceOrderRepositoryTest extends EcommerceOrderRepositoryTemplate {

    @Test
    public void shouldBeCreateAnEntity() {
        // given
        EcommerceOrder ecommerceOrder = giveEcommerceOrder();

        // when
        EcommerceOrder saved = ecommerceOrderRepository.save(ecommerceOrder);

        // then
        assertNotNull(saved);
        assertNotNull(saved.getId());
    }

}
