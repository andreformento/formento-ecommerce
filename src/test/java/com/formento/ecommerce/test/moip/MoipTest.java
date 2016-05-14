package com.formento.ecommerce.test.moip;

import br.com.moip.API;
import br.com.moip.Client;
import br.com.moip.authentication.Authentication;
import br.com.moip.authentication.BasicAuth;
import br.com.moip.request.*;
import br.com.moip.resource.Order;
import br.com.moip.resource.Payment;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.UUID;

import static org.junit.Assert.assertNotNull;

public class MoipTest {

    private static final String CC_HASH = "HhL0kbhfid+jwgj5l6Kt9EPdetDxQN8s7uKUHDYxDC/XoULjzik44rSda3EcWuOcL17Eb8JjWc1JI7gsuwg9P0rJv1mJQx+d3Dv1puQYz1iRjEWWhnB1bw0gTvnnC/05KbWN5M8oTiugmhVK02Rt2gpbcTtpS7VWyacfgesBJFavYYMljYg8p2YGHXkXrMuQiOCeemKLk420d0OTMBba27jDVVJ663HZDrObnjFXJH/4B5irkj+HO5genV+V4PYoLcOESG4nrI3oFAsMGsLLcdJo0NNvkEmJpn0e9GzureKKFYisYU+BEd9EMr/odS0VMvOYRV65HbPTspIkjl2+3Q==";
    private static final String TOKEN = "01010101010101010101010101010101";
    private static final String KEY = "ABABABABABABABABABABABABABABABABABABABAB";

    private UUID uuid;

    @Before
    public void init() {
        this.uuid = UUID.randomUUID();
    }

    @Test
    public void shouldBePaidByCreditCard() {
        // Autenticando por BasicAuth
        Authentication auth = new BasicAuth(TOKEN, KEY);
        assertNotNull(auth);

        // Após deifinir o tipo de autenticação, é necessário gerar o client, informando em qual environment você quer executar suas ações:
        Client client = new Client(Client.SANDBOX, auth);
        assertNotNull(client);

        // Agora você pode instanciar a Api:
        API api = new API(client);
        assertNotNull(api);

        // Criando um Pedido
        CustomerRequest customerOwnId = new CustomerRequest()
                .ownId("customer_own_id")
                .fullname("Jose da Silva")
                .email("sandbox_v2_1401147277@email.com");
        assertNotNull(customerOwnId);

        Order createdOrder = api.order().create(new OrderRequest()
                .ownId("order_own_id")
                .addItem("Nome do produto", 1, "Mais info...", 100)
                .customer(customerOwnId));
        assertNotNull(createdOrder);

        // Criando um pagamento
        HolderRequest holderRequest = new HolderRequest()
                .fullname("Jose Portador da Silva")
                .birthdate("1988-10-10")
                .phone(new PhoneRequest()
                        .setAreaCode("11")
                        .setNumber("55667788")
                )
                .taxDocument(TaxDocumentRequest.cpf("22222222222"));
        assertNotNull(holderRequest);

        CreditCardRequest creditCardRequest = new CreditCardRequest()
                .hash(CC_HASH)
                .holder(holderRequest);
        assertNotNull(creditCardRequest);

        FundingInstrumentRequest fundingInstrumentRequest = new FundingInstrumentRequest().creditCard(creditCardRequest);
        assertNotNull(fundingInstrumentRequest);

        PaymentRequest paymentRequest = new PaymentRequest()
                .orderId(createdOrder.getId())
                .installmentCount(1)
                .fundingInstrument(
                        fundingInstrumentRequest
                );
        Payment createdPayment = api.payment().create(paymentRequest);
        assertNotNull(createdPayment);
    }

    @Test
    public void shouldBePaidByBoleto() {
        // Autenticando por BasicAuth
        Authentication auth = new BasicAuth(TOKEN, KEY);
        assertNotNull(auth);

        // Após deifinir o tipo de autenticação, é necessário gerar o client, informando em qual environment você quer executar suas ações:
        Client client = new Client(Client.SANDBOX, auth);
        assertNotNull(client);

        // Agora você pode instanciar a Api:
        API api = new API(client);
        assertNotNull(api);

        // Criando um Pedido
        CustomerRequest customerOwnId = new CustomerRequest()
                .ownId("customer_own_id")
                .fullname("Jose da Silva")
                .email("sandbox_v2_1401147277@email.com");
        assertNotNull(customerOwnId);

        Order createdOrder = api.order().create(new OrderRequest()
//                .ownId("order_own_id")
                .ownId(uuid.toString())
                .addItem("Nome do produto", 1, "Mais info...", 100)
                .customer(customerOwnId));
        assertNotNull(createdOrder);

        // Boleto
        InstructionLinesRequest instructionLinesRequest = new InstructionLinesRequest()
                .first("Primeira linha")
                .second("Segunda linha")
                .third("Terceira linha");
        assertNotNull(instructionLinesRequest);

        BoletoRequest boletoRequest = new BoletoRequest()
                .expirationDate(new ApiDateRequest().date(new GregorianCalendar(2020, Calendar.NOVEMBER, 10).getTime()))
                .logoUri("http://logo.com")
                .instructionLines(instructionLinesRequest);
        assertNotNull(boletoRequest);

        FundingInstrumentRequest fundingInstrumentRequest = new FundingInstrumentRequest().boleto(boletoRequest);
        assertNotNull(fundingInstrumentRequest);

        PaymentRequest paymentRequest = new PaymentRequest()
                .orderId(createdOrder.getId())
                .installmentCount(1)
                .fundingInstrument(fundingInstrumentRequest);
        Payment createdPaymentBoleto = api.payment().create(paymentRequest);
        assertNotNull(createdPaymentBoleto);
    }

}
