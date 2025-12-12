package apiui.demoqa;

import apiui.helpers.ActionsWithBooks;
import apiui.helpers.AuthorizationApi;
import apiui.models.AddBookReq;
import apiui.models.AddBookResp;
import apiui.models.LoginReq;
import apiui.models.LoginResp;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static apiui.helpers.TestData.*;
import static io.restassured.RestAssured.given;

@Tag("ApiUI")
public class ImprovedApiUiTests extends BaseTest {

    ProfilePage profilePage = new ProfilePage();

    @Test
    @DisplayName("API+UI авторизация/добавление книги/проверка в коллекции/удаление/проверка №2")
    public void finalTest2() {

        LoginReq loginReq = new LoginReq(DEFAULT_USERNAME,DEFAULT_PASSWORD);

        LoginResp loginResp = given()
                .contentType(ContentType.JSON)
                .body(loginReq)
                .when()
                .post("/Account/v1/Login")
                .then()
                .log().all()
                .statusCode(200)
                .extract().as(LoginResp.class);

        AddBookReq addBookReq = new AddBookReq(loginResp.getUserId(),DEFAULT_ISBN);

        given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + loginResp.getToken())
                .queryParams("UserId", loginResp.getUserId())
                .when()
                .delete("/BookStore/v1/Books")
                .then()
                .statusCode(204);

        AddBookResp addBookResp = given()
                .contentType(ContentType.JSON)
                .body(addBookReq)
                .header("Authorization", "Bearer " + loginResp.getToken())
                .when()
                .post("/Bookstore/v1/Books")
                .then()
                .log().all()
                .statusCode(201)
                .extract().as(AddBookResp.class);

        profilePage
                .getCookies(loginResp)
                .navigateToProfile()
                .checkingAddedBook(loginResp)
                .deleteBookAndfightWithAlert()
                .checkingDeletedBook();
    }

    @Test
    @DisplayName("Удаление книги из списка в профиле")
    void finalTest3() {
        LoginResp responseAfterLogin = AuthorizationApi.logIn(DEFAULT_USERNAME,DEFAULT_PASSWORD);
        ActionsWithBooks.deleteAllBooks(responseAfterLogin);
        ActionsWithBooks.addBook(responseAfterLogin, DEFAULT_ISBN);

        profilePage
                .getCookies(responseAfterLogin)
                .navigateToProfile()
                .checkingAddedBook(responseAfterLogin)
                .deleteBookAndfightWithAlert()
                .checkingDeletedBook();
    }
}
