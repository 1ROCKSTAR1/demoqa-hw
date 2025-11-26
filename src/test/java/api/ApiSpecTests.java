package api;

import api.models.*;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static api.specs.CreateUserSpec.*;
import static api.specs.GetUserSpec.getUser;
import static api.specs.GetUserSpec.getUserResponse;
import static api.specs.LoginSpec.loginRequestSpec;
import static api.specs.LoginSpec.loginResponseSpec;
import static com.codeborne.selenide.logevents.SelenideLogger.step;
import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.MatcherAssert.assertThat;

public class ApiSpecTests {

    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = "https://reqres.in";
        RestAssured.basePath = "/api";
    }

    Header header = new Header("x-api-key", "reqres-free-v1");

    @Test
    @DisplayName("Создать пользователя")
    public void createUserTest() {
        UserCreateData newUser = new UserCreateData("Tom","cleaner");

        UserCreateResponse response = step("Make request", ()->
                given(createSimpleUser)
                        .body(newUser)
                        .when()
                        .post("/users")
                        .then()
                        .spec(createSimpleUserSpecResponse)
                        .extract().as(UserCreateResponse.class));

        step("Check response", () -> {
            assertThat(response.getId(), is(notNullValue()));
            assertThat(response.getName(), is("Tom"));
            assertThat(response.getJob(), is("cleaner"));
        });
    }

    @Test
    @DisplayName("НЕГАТИВНЫЙ ТЕСТ. Создание пользователя с битым body.")
    public void createFailUserTest() {
        String newNegativeUser = "{ ";

        BadRequestFormatResponse response = step("Make request", ()->
                given(createSimpleUser)
                        .body(newNegativeUser)
                        .when()
                        .post()
                        .then()
                        .spec(createWrongUserSpecResponse)
                        .extract().as(BadRequestFormatResponse.class));

        step("Check response", () -> {
            assertThat(response.getError(), equalTo("Bad Request"));
            assertThat(response.getMessage(), equalTo("Invalid request format"));
        });
    }

    @Test
    @DisplayName("Получить пользователя по id")
    public void getOneUserTest() {

        UserResponse response = step("Make request", ()->
                given(getUser)
                        .when()
                        .get("/users/2")
                        .then()
                        .spec(getUserResponse)
                        .extract().jsonPath()
                        .getObject("data", UserResponse.class));

        step("Check response", () -> {
            assertThat(response.getId(), notNullValue());
            assertThat(response.getEmail(), endsWith("@reqres.in"));
            assertThat(response.getAvatar(), endsWith(".jpg"));
        });
    }

    @Test
    @DisplayName("Авторизация пользователя")
    public void successfulLoginTest() {

        UserLoginData user = new UserLoginData("eve.holt@reqres.in","cityslicka");

        SuccessfulLoginResponse response = step("Make request", ()->
                given(loginRequestSpec)
                        .body(user)
                        .when()
                        .post("/login")
                        .then()
                        .spec(loginResponseSpec)
                        .extract().as(SuccessfulLoginResponse.class));

        step("Check response", () -> {
            Assertions.assertThat(response.getToken())
                    .isNotNull()
                    .isNotBlank()
                    .hasSizeGreaterThan(10)
                    .matches("^[a-zA-Z0-9]+$");
        });
    }

    @Test
    @DisplayName("Получение списка пользователей")
    public void getAllUsersTest() {

        List<UserResponse> users = step("Make request", ()->
                given(getUser)
                        .when()
                        .queryParam("page", "2")
                        .get("/users")
                        .then()
                        .log().body()
                        .extract().jsonPath()
                        .getList("data", UserResponse.class)
                        .stream()
                        .toList());

        step("Check response", () -> {
            org.junit.jupiter.api.Assertions.assertTrue(users
                    .stream()
                    .allMatch(a -> a.getEmail().endsWith("@reqres.in")));
        });
    }
}
