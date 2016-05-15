package com.formento.ecommerce.user.model.service;

import com.formento.ecommerce.exception.UnauthorizedEcommerceException;
import com.formento.ecommerce.security.UserAuthentication;
import com.formento.ecommerce.security.component.JwtTokenUtil;
import com.formento.ecommerce.security.service.AuthenticationRestService;
import com.formento.ecommerce.user.model.User;
import com.formento.ecommerce.user.model.repository.UserRepository;
import com.formento.ecommerce.user.model.service.component.EncoderComponent;
import com.formento.ecommerce.user.model.service.validation.UserValidator;
import com.google.common.base.Preconditions;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Service
public class UserServiceProvider implements UserService {

    private static final String USER_BY_EMAIL_AND_PASSWORD_NOT_FOUND = "user.byEmailAndPasswordNotFound";
    private static final String USER_BY_EMAIL_NOT_FOUND = "user.byEmailNotFound";
    private static final String USER_INVALID_TOKEN = "user.invalidToken";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private EncoderComponent encoderComponent;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private AuthenticationRestService authenticationRestService;

    private String getEncodedPassword(String notEncodedPassword) {
        return encoderComponent.encode(notEncodedPassword);
    }

    private String getToken(UserAuthentication userAuthentication) {
        return jwtTokenUtil.generateToken(userAuthentication);
    }

    @Override
    public User create(User user) {
        Preconditions.checkNotNull(user, "user.create.notValid");
        Preconditions.checkNotNull(user.getPassword(), "user.create.invalidPassword");
        Preconditions.checkNotNull(user.getEmail(), "user.create.invalidMail");

        User newUser = new User
                .Builder()
                .withSelf(user)
                .withPassword(getEncodedPassword(user.getPassword()))
                .withCreationDate(LocalDate.now())
                .withUpdateDate(LocalDate.now())
                .withLastLogin(LocalDateTime.now())
                .withToken(getToken(user))
                .build();

        userValidator.beforeCreate(newUser);
        User save = userRepository.save(newUser);
        authenticationRestService.createAuthentication(user);
        return save;
    }

    @Override
    public User updateToken(UserAuthentication userAuthentication) {
        User byEmailAndPassword = getByEmail(userAuthentication.getEmail()).orElseThrow(() -> new UnauthorizedEcommerceException(USER_BY_EMAIL_AND_PASSWORD_NOT_FOUND));

        User userUpdateToken = new User
                .Builder()
                .withSelf(byEmailAndPassword)
                .withLastLogin(LocalDateTime.now())
                .withToken(getToken(byEmailAndPassword))
                .build();

        User save = userRepository.save(userUpdateToken);
        authenticationRestService.createAuthentication(userAuthentication);
        return save;
    }

    @Override
    public User getByEmailAndPassword(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password).orElseThrow(() -> new UnauthorizedEcommerceException(USER_BY_EMAIL_AND_PASSWORD_NOT_FOUND));
    }

    @Override
    public Integer countByEmail(String email) {
        return userRepository.countByEmail(email);
    }

    @Override
    public Optional<User> getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> getUserOfSession() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserAuthentication) {
            UserAuthentication userAuthentication = (UserAuthentication) authentication.getPrincipal();
            return userRepository.findByEmail(userAuthentication.getEmail());
        } else {
            return Optional.empty();
        }
    }

    @Override
    public void validateTokenSaved(String email, String authToken) {
        User user = getByEmail(email).orElseThrow(() -> new UnauthorizedEcommerceException(USER_BY_EMAIL_NOT_FOUND));
        if (!user.getToken().equals(authToken)) {
            throw new UnauthorizedEcommerceException(USER_INVALID_TOKEN);
        }
    }

}
