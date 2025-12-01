package api;

import api.models.*;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class ApiTests {

    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = "https://reqres.in";
        RestAssured.basePath = "/api";
    }

    Header header = new Header("x-api-key", "reqres_8b586696dd19457e8900cb0fe6050d2e");

    @Test
    @DisplayName("Создать пользователя")
    public void createUserTest() {
        UserCreateData newUser = new UserCreateData("Tom","cleaner");

        UserCreateResponse response = given()
                .filter(new AllureRestAssured())
                .header(header)
                .log().uri()
                .log().body()
                .log().headers()
                .body(newUser)
                .contentType(ContentType.JSON)
                .when()
                .post("/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .extract().as(UserCreateResponse.class);

        assertThat(response.getId(), is(notNullValue()));
        assertThat(response.getName(), is("Tom"));
        assertThat(response.getJob(), is("cleaner"));
    }

    @Test
    @DisplayName("НЕГАТИВНЫЙ ТЕСТ. Создание пользователя с битым body.")
    public void createFailUserTest() {
        String newNegativeUser = "{ ";

        BadRequestFormatResponse response = given()
                .filter(new AllureRestAssured())
                .header(header)
                .log().uri()
                .log().body()
                .log().headers()
                .body(newNegativeUser)
                .contentType(ContentType.JSON)
                .when()
                .post("/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .extract().as(BadRequestFormatResponse.class);

        assertThat(response.getError(),equalTo("Bad Request"));
        assertThat(response.getMessage(),equalTo("Invalid request format"));
    }

    @Test
    @DisplayName("Получить пользователя по id")
    public void getOneUserTest() {

        UserResponse response = given()
                .filter(new AllureRestAssured())
                .header(header)
                .log().uri()
                .log().body()
                .log().headers()
                .contentType(ContentType.JSON)
                .when()
                .get("/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().jsonPath()
                .getObject("data", UserResponse.class);

        assertThat(response.getId(), notNullValue());
        assertThat(response.getEmail(), endsWith("@reqres.in"));
        assertThat(response.getAvatar(), endsWith(".jpg"));
    }

    @Test
    @DisplayName("Авторизация пользователя")
    public void successfulLoginTest() {

        UserLoginData user = new UserLoginData("eve.holt@reqres.in","cityslicka");

        SuccessfulLoginResponse response = given()
                .filter(new AllureRestAssured())
                .header(header)
                .log().uri()
                .log().body()
                .log().headers()
                .body(user)
                .contentType(ContentType.JSON)
                .when()
                .post("/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().as(SuccessfulLoginResponse.class);

        assertThat(response.getToken())
                .isNotNull()
                .isNotBlank()
                .hasSizeGreaterThan(10)
                .matches("^[a-zA-Z0-9]+$");
    }

    @Test
    @DisplayName("Получение списка пользователей")
    public void getAllUsersTest() {

        List<UserResponse> users = given()
                .filter(new AllureRestAssured())
                .contentType(ContentType.JSON)
                .header(header)
                .log().uri()
                .log().body()
                .log().headers()
                .when()
                .queryParam("page", "2")
                .get("/users")
                .then()
                .log().body()
                .extract().jsonPath()
                .getList("data", UserResponse.class)
                .stream()
                .toList();

        Assertions.assertTrue(users
                .stream()
                .allMatch(a->a.getEmail().endsWith("@reqres.in")));
    }
}
