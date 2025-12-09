package apiui.demoqa;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.codeborne.selenide.logevents.SelenideLogger.step;
import static io.restassured.RestAssured.given;

public class SimpleApiUiTests extends BaseTest {

    @Test
    @DisplayName("Обычный UI тест с авторизацией")
    public void loginUITest() {

        step("Open login page", () ->
                open("/login"));

        step("Fill the login field", () ->
                $("#userName").setValue(login));

        step("Fill the password field", () ->
                $("#password").setValue(password));

        step("Click on the login button", () ->
                $("#login").click());

        step("Assert of login", () ->
                $("#userName-value").shouldHave(text(login)));
    }

    @Test
    public void loginApiUiTest() {

        String authData = "{\"userName\":\"Tom99\",\"password\":\"P@ssword1\"}";

        step("Get cookie", () -> {
            Response auth = given()
                    .contentType(ContentType.JSON)
                    .body(authData)
                    .when()
                    .post("/Account/v1/Login")
                    .then()
                    .log().all()
                    .statusCode(200)
                    .extract().response();


        open("/favicon.ico");
        getWebDriver().manage().addCookie(new Cookie("userID", auth.path("userId")));
        getWebDriver().manage().addCookie(new Cookie("expires", auth.path("expires")));
        getWebDriver().manage().addCookie(new Cookie("token", auth.path("token")));
    });

        step("Go to the site and make an assert", () -> {
        open("/profile");
        $("#userName-value").shouldHave(text(login));
    });
}
}
