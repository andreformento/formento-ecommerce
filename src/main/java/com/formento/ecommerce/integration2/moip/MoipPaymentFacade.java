package com.formento.ecommerce.integration2.moip;

//@Component
public class MoipPaymentFacade {/*implements CreditCardPaymentFacade {

    @Autowired
    private MoipConfiguration moipConfiguration;

    @Autowired
    private MoipApi moipApi;

    @Autowired
    private EcommerceOrderService ecommerceOrderService;

    @Override
    public PaymentEcommerce makePayment(ShoppingCart shoppingCart, PaymentInstallment paymentInstallment, CreditCardHolder creditCardHolder) {
        User user = shoppingCart.getUser();
        CustomerRequest customerOwnId = getCustomerRequest(user);

        API api = moipApi.getApi();

        EcommerceOrder ecommerceOrder = createEcommerceOrder(shoppingCart);

        Order createdOrder = getOrderRequest(shoppingCart, customerOwnId, api, ecommerceOrder);

        createPayment(creditCardHolder, paymentInstallment, api, createdOrder);

        return ecommerceOrder;
    }

    private Payment createPayment(CreditCardHolder creditCardHolder, PaymentInstallment paymentInstallment, API api, Order createdOrder) {
        // Criando um pagamento
        HolderRequest holderRequest = new HolderRequest()
                .fullname(creditCardHolder.getName())
                .birthdate(LocalDateUtil.format(creditCardHolder.getBirthDate(), moipConfiguration.getDateFormat()))
                .taxDocument(TaxDocumentRequest.cpf(creditCardHolder.getTaxDocument().getNumber()));

        creditCardHolder
                .getPhone()
                .ifPresent(phone -> holderRequest.phone(new PhoneRequest()
                        .setAreaCode(phone.getAreaCode())
                        .setNumber(phone.getNumber())));

        CreditCardRequest creditCardRequest = new CreditCardRequest()
                .hash(moipConfiguration.getCcHash())
                .holder(holderRequest);

        FundingInstrumentRequest fundingInstrumentRequest = new FundingInstrumentRequest().creditCard(creditCardRequest);

        PaymentRequest paymentRequest = new PaymentRequest()
                .orderId(createdOrder.getId())
                .installmentCount(paymentInstallment.getCount())
                .fundingInstrument(fundingInstrumentRequest);

        return api.payment().create(paymentRequest);
    }

    private EcommerceOrder createEcommerceOrder(ShoppingCart shoppingCart) {
        return ecommerceOrderService.create(
                new EcommerceOrder.Builder()
                        .withShoppingCart(shoppingCart)
                        .withStatusEcommerceOrder(StatusEcommerceOrder.CREATED)
                        .build()
        );
    }

    private Order getOrderRequest(ShoppingCart shoppingCart, CustomerRequest customerOwnId, API api, EcommerceOrder ecommerceOrder) {
        OrderRequest orderRequest = new OrderRequest()
                .ownId(ecommerceOrder.getId().toString())
                .customer(customerOwnId);
        shoppingCart
                .getItemShoppingCarts()
                .stream()
                .forEach(itemShoppingCart ->
                        orderRequest.addItem(
                                itemShoppingCart.getProduct().getName(),
                                itemShoppingCart.getQuantity(),
                                itemShoppingCart.getProduct().getDescription(),
                                itemShoppingCart.getProductPrice().multiply(BigDecimal.valueOf(100)).setScale(0, RoundingMode.DOWN).intValue()
                        ));

        return api.order().create(orderRequest);
    }

    private CustomerRequest getCustomerRequest(User user) {
        // Criando um Pedido
        return new CustomerRequest()
                .ownId(user.getId().toString())
                .fullname(user.getName())
                .email(user.getEmail());
    }
*/
}
