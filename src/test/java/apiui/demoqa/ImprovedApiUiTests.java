package apiui.demoqa;

import apiui.models.AddBookReq;
import apiui.models.AddBookResp;
import apiui.models.LoginReq;
import apiui.models.LoginResp;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import static apiui.helpers.TestData.*;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.restassured.RestAssured.given;

public class ImprovedApiUiTests extends BaseTest {

    @Test
    public void addBookApiUiTest () {

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

        open("/favicon.ico");
        getWebDriver().manage().addCookie(new Cookie("userID", loginResp.getUserId()));
        getWebDriver().manage().addCookie(new Cookie("expires", loginResp.getExpires()));
        getWebDriver().manage().addCookie(new Cookie("token",loginResp.getToken()));

        open("/profile");
        $("#userName-value").shouldHave(text(loginResp.getUsername()));
        $("a[href='/profile?book=9781449365035']").shouldHave(text(DEFAULT_TITLE));
    }
}
