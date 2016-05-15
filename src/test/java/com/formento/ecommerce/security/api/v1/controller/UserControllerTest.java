package com.formento.ecommerce.security.api.v1.controller;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.formento.ecommerce.user.model.User;
import com.formento.ecommerce.user.model.service.UserService;
import com.formento.ecommerce.user.model.template.UserTemplate;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    @Mock
    private UserService userService;

    private UserController userController;

    private MockMvc mockMvc;

    @BeforeClass
    public static void initClass() {
        FixtureFactoryLoader.loadTemplates("com.formento.ecommerce.user.model.template");
    }

    @Before
    public void init() {
        this.userController = new UserController(userService);
        this.mockMvc = standaloneSetup(this.userController).build();
    }

    @Test
    public void shouldToReturnAValidUser() throws Exception {
        // given
        User user = Fixture.from(User.class).gimme(UserTemplate.VALID_USER_NO_ID);
        assertNotNull(user);

        // when
        when(userService.getUserOfSession()).thenReturn(Optional.of(user));

        ResultActions resultActions = this.mockMvc.perform(get("/api/v1/users").accept(MediaType.APPLICATION_JSON));

        // then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(containsString("Andre Formento")))
                .andExpect(content().string(containsString("andreformento.sc@gmail.com")))
                .andExpect(content().string(not(containsString("myP@ssw0rd"))))
                .andExpect(content().string(containsString("token")));
    }

}