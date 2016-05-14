package com.formento.ecommerce.integration.moip;

import com.formento.ecommerce.FormentoEcommerceApplication;
import com.formento.ecommerce.ecommerceOrder.model.EcommerceOrder;
import com.formento.ecommerce.ecommerceOrder.model.StatusEcommerceOrder;
import com.formento.ecommerce.ecommerceOrder.repository.EcommerceOrderRepositoryTemplate;
import com.formento.ecommerce.ecommerceOrder.service.EcommerceOrderService;
import com.formento.ecommerce.shoppingCart.model.ShoppingCart;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = FormentoEcommerceApplication.class)
public class MoipOrderIntegrationFacadeTest extends EcommerceOrderRepositoryTemplate {

    @Autowired
    private MoipApi moipApi;

    @Autowired
    private EcommerceOrderService ecommerceOrderService;

    @Test
    public void sholdDoAnOrder() {
        // given
        MoipOrderIntegrationFacade moipOrderIntegrationFacade = new MoipOrderIntegrationFacade(moipApi, ecommerceOrderService, giveShoppingCart());

        // when
        EcommerceOrder ecommerceOrder = moipOrderIntegrationFacade.makeOrder();

        // then
        assertNotNull(ecommerceOrder);
        assertNotNull(ecommerceOrder.getId());
        assertNotNull(ecommerceOrder.getUser());
        assertNotNull(ecommerceOrder.getIntegrationId());
        assertEquals(StatusEcommerceOrder.CREATED, ecommerceOrder.getStatusEcommerceOrder());
    }

}
