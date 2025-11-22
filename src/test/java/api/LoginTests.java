package api;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

public class LoginTests {

    @Test
    public void createUserTest() {
        UserCreateData newUser = new UserCreateData("Tom","cleaner");

        given()
                .log().uri()
                .header("X-API-Key", "reqres-free-v1")
                .log().headers()
                .body(newUser)
                .contentType(ContentType.JSON)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(201);
    }

    @Test
    public void createFailUserTest() {
        String newNegativeUser = "{ \"name\": \"Tom\"}";

        given()
                .log().uri()
                .header("X-API-Key", "reqres-free-v1")
                .log().headers()
                .body(newNegativeUser)
                .contentType(ContentType.JSON)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(400);
    }

    @Test
    public void getOneUserTest() {
        given()
                .log().uri()
                .header("X-API-Key", "reqres-free-v1")
                .log().headers()
                .contentType(ContentType.JSON)
                .when()
                .get("https://reqres.in/api/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200);
    }

    @Test
    public void successfulLoginTest() {

        UserLoginData user = new UserLoginData("eve.holt@reqres.in","cityslicka");

        given()
                .log().uri()
                .header("X-API-Key", "reqres-free-v1")
                .log().headers()
                .body(user)
                .contentType(ContentType.JSON)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("token", is("QpwL5tke4Pnpja7X4"));
    }

    @Test
    public void getAllUsersTest() {

        List<UserResponse> users = given().contentType(ContentType.JSON)
                .log().uri()
                .header("X-API-Key", "reqres-free-v1")
                .log().headers()
                .when()
                .get("https://reqres.in/api/users?page=2")
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
