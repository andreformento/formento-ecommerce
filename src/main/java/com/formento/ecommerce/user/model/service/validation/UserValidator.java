package com.formento.ecommerce.user.model.service.validation;


import com.formento.ecommerce.user.model.User;

public interface UserValidator {

    void beforeCreate(User user);

}
