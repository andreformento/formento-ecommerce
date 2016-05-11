package com.formento.ecommerce.integration2.moip;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Component
@Scope("singleton")
public class MoipConfiguration {

    @Value("${integration.moip.ccHash}")
    private String ccHash;

    @Value("${integration.moip.token}")
    private String token;

    @Value("${integration.moip.key}")
    private String key;

    @Value("${integration.moip.dateFormat}")
    private String dateFormat;

}
