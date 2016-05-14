package com.formento.ecommerce.integration.moip;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.formento.ecommerce.FormentoEcommerceApplication;
import com.formento.ecommerce.ecommerceOrder.model.EcommerceOrder;
import com.formento.ecommerce.ecommerceOrder.repository.EcommerceOrderRepositoryTemplate;
import com.formento.ecommerce.ecommerceOrder.service.EcommerceOrderService;
import com.formento.ecommerce.integration.PaymentFacade;
import com.formento.ecommerce.payment.model.MethodPayment;
import com.formento.ecommerce.payment.model.Payment;
import com.formento.ecommerce.payment.model.UniqueMethodPayment;
import com.formento.ecommerce.payment.model.boleto.BoletoEcommerceFundingInstrument;
import com.formento.ecommerce.payment.model.boleto.template.BoletoEcommerceFundingInstrumentTemplate;
import com.formento.ecommerce.shoppingCart.model.ShoppingCart;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = FormentoEcommerceApplication.class)
public class BoletoPaymentFacadeTest extends EcommerceOrderRepositoryTemplate {

    @Autowired
    private MoipApi moipApi;

    @Autowired
    private EcommerceOrderService ecommerceOrderService;

    @BeforeClass
    public static void initClass() {
        FixtureFactoryLoader.loadTemplates("com.formento.ecommerce.user.model.template");
        FixtureFactoryLoader.loadTemplates("com.formento.ecommerce.ecommerceOrder.model.template");
        FixtureFactoryLoader.loadTemplates("com.formento.ecommerce.payment.model.boleto.template");
    }

    @Test
    public void shouldToDoPayment() {
        // given
        ShoppingCart shoppingCart = giveShoppingCart();
        MoipOrderIntegrationFacade moipOrderIntegrationFacade = new MoipOrderIntegrationFacade(moipApi, ecommerceOrderService, shoppingCart);

        MethodPayment methodPayment = new UniqueMethodPayment(shoppingCart.getTotalValue());
        BoletoEcommerceFundingInstrument fundingInstrument = Fixture.from(BoletoEcommerceFundingInstrument.class).gimme(BoletoEcommerceFundingInstrumentTemplate.VALID);

        // when
        EcommerceOrder ecommerceOrder = moipOrderIntegrationFacade.makeOrder();
        PaymentFacade paymentFacade = new BoletoPaymentFacade(moipApi, ecommerceOrder, methodPayment, fundingInstrument);
        Payment payment = paymentFacade.makePayment();

        // then
        assertNotNull(payment);
        assertNotNull(payment.getFundingInstrument());
        assertNotNull(payment.getMethodPayment());
        assertNotNull(payment
                .getEcommerceOrder()
                .orElseThrow(() -> new AssertionError("Order not found"))
                .getIntegrationId()
                .orElseThrow(() -> new AssertionError("Integration id not found")));
    }

}
