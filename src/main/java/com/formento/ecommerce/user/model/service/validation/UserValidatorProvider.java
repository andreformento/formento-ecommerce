package com.formento.ecommerce.user.model.service.validation;

import com.formento.ecommerce.exception.BusinessEcommerceException;
import com.formento.ecommerce.user.model.User;
import com.formento.ecommerce.user.model.service.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@NoArgsConstructor
@Component
public class UserValidatorProvider implements UserValidator {

    @Autowired
    private UserService userService;

    @Override
    public void beforeCreate(User user) {
        Integer count = userService.countByEmail(user.getEmail());
        if (count > 0) {
            throw new BusinessEcommerceException("user.validator.emailExists");
        }
    }

}
