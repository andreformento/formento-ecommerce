package com.formento.ecommerce.integration.moip;

import br.com.moip.API;
import br.com.moip.Client;
import br.com.moip.authentication.Authentication;
import br.com.moip.authentication.BasicAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class MoipApi {

    @Autowired
    private MoipConfiguration moipConfiguration;

    public API getApi() {
        // Autenticando por BasicAuth
        Authentication auth = new BasicAuth(moipConfiguration.getToken(), moipConfiguration.getKey());

        // Após deifinir o tipo de autenticação, é necessário gerar o client, informando em qual environment você quer executar suas ações:
        Client client = new Client(Client.SANDBOX, auth);

        // Agora você pode instanciar a Api:
        return new API(client);
    }

}
