package com.epam.hackathon.emotional_help.testing.api.steps.authSteps;

import com.epam.hackathon.emotional_help.testing.api.dataProviders.TestDataProvider;
import com.epam.hackathon.emotional_help.testing.api.dtos.UserDto;
import com.epam.hackathon.emotional_help.testing.api.dataProviders.Endpoints;
import com.epam.hackathon.emotional_help.testing.api.dataProviders.SharedTestData;
import com.epam.hackathon.emotional_help.testing.api.dataProviders.UserProvider;
import com.epam.hackathon.emotional_help.testing.api.utils.RequestUtils;
import com.epam.hackathon.emotional_help.testing.api.utils.ResponseUtils;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;


public class Registration {
    private UserDto user;
    private final Map<String, Object> queryParam = new HashMap<>();

    private final static Logger logger = LoggerFactory.getLogger(Registration.class);


    @When("Check registration fail if username existing")
    public void checkRegistrationFailIfUsernameExisting() {
        user = UserProvider.getRandomUserWithExistingUsername();
        queryParam.put("anonymousUuid", TestDataProvider.getPropertyValue("anonymousUuid"));
        RequestUtils.postByQueryParam(Endpoints.SIGN_UP.getEndpoint(), user, queryParam);
    }

    @Then("Validate existing username error message")
    public void validateExistingUsernameErrorMessage() {
        String existingUsernameErrorMessage = "Username " + "'" + SharedTestData.getUsername() + "'" + " is taken";
        Assertions.assertThat(ResponseUtils.getResponse().extract().asString())
                .isEqualTo(existingUsernameErrorMessage);
        logger.info("Registration by existing username error message is -{}", ResponseUtils.getResponse().extract().asString());
    }

    @When("Check registration fail if email is existing")
    public void checkRegistrationFailIfEmailIsExisting() {
        UserDto user = UserProvider.getRandomUserWithExistingEmail();
        queryParam.put("anonymousUuid", TestDataProvider.getPropertyValue("anonymousUuid"));
        RequestUtils.postByQueryParam(Endpoints.SIGN_UP.getEndpoint(), user, queryParam);
    }

    @Then("Validate existing email error message")
    public void validateExistingEmailErrorMessage() {
        String existingEmailErrorMessage = "Email " + "'" + SharedTestData.getEmail() + "'" + " is taken";
        Assertions.assertThat(ResponseUtils.getResponse().extract().asString())
                .isEqualTo(existingEmailErrorMessage);
        logger.info("Registration by existing email error message is -{}", ResponseUtils.getResponse().extract().asString());
    }

    @When("Check registration fail if password less than seven character")
    public void checkRegistrationFailIfPasswordLessThanSevenCharacter() {
        UserDto user = UserProvider.getRandomUserWithInvalidPassword();
        queryParam.put("anonymousUuid", TestDataProvider.getPropertyValue("anonymousUuid"));
        RequestUtils.postByQueryParam(Endpoints.SIGN_UP.getEndpoint(), user, queryParam);
    }

    @Then("Validate invalid password error message")
    public void validateInvalidPasswordErrorMessage() {
        String invalidPasswordErrorMessage = "Password must be at least 7 characters";
        Assertions.assertThat(ResponseUtils.getResponse().extract().asString())
                .isEqualTo(invalidPasswordErrorMessage);
        logger.info("Registration by invalid password error message is - {}", ResponseUtils.getResponse().extract().asString());
    }

    @When("Register a random user")
    public void registerARandomUser() {
        user = UserProvider.getRandomUser();
        SharedTestData.setUsername(user.getUsername());
        SharedTestData.setPassword(user.getPassword());
        SharedTestData.setEmail(user.getEmail());
        queryParam.put("anonymousUuid", TestDataProvider.getPropertyValue("anonymousUuid"));
        RequestUtils.postByQueryParam(Endpoints.SIGN_UP.getEndpoint(), user, queryParam);
        SharedTestData.setToken(ResponseUtils.getResponse().extract().asString());
        logger.info("Token from response is - {}",ResponseUtils.getResponse().extract().asString());
    }
}
