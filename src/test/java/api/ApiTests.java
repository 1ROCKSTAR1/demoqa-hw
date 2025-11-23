package api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.emptyString;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.matchesPattern;

public class ApiTests {

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

        given()
                .log().uri()
                .header(header)
                .log().headers()
                .body(newUser)
                .contentType(ContentType.JSON)
                .when()
                .post(baseURI + basePath + "/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body("name",equalTo("Tom"))
                .body("job",equalTo("cleaner"))
                .body("id", notNullValue());
    }

    @Test
    @DisplayName("НЕГАТИВНЫЙ ТЕСТ. Создание пользователя без должности.")
    public void createFailUserTest() {
        String newNegativeUser = "{ \"name\": \"Tom\"}";

        given()
                .log().uri()
                .header(header)
                .log().headers()
                .body(newNegativeUser)
                .contentType(ContentType.JSON)
                .when()
                .post(baseURI + basePath + "/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("message",equalTo("Missing job"));
    }

    @Test
    @DisplayName("Получить пользователя по id")
    public void getOneUserTest() {
        given()
                .log().uri()
                .header(header)
                .log().headers()
                .contentType(ContentType.JSON)
                .when()
                .get(baseURI + basePath + "/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("data.id", notNullValue())
                .body("data.email",endsWith("@reqres.in"))
                .body("data.avatar", endsWith(".jpg"));
    }

    @Test
    @DisplayName("Авторизация пользователя")
    public void successfulLoginTest() {

        UserLoginData user = new UserLoginData("eve.holt@reqres.in","cityslicka");

        given()
                .log().uri()
                .header(header)
                .log().headers()
                .body(user)
                .contentType(ContentType.JSON)
                .when()
                .post(baseURI + basePath + "/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("token", notNullValue())
                .body("token", not(emptyString()))
                .body("token.length()", greaterThan(10))
                .body("token", matchesPattern("^[a-zA-Z0-9]+$"));
    }

    @Test
    @DisplayName("Получение списка пользователей")
    public void getAllUsersTest() {

        List<UserResponse> users = given().contentType(ContentType.JSON)
                .log().uri()
                .header(header)
                .log().headers()
                .when()
                .get(baseURI + basePath + "/users?page=2")
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
