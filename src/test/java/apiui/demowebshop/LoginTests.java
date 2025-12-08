package apiui.demowebshop;

import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.codeborne.selenide.logevents.SelenideLogger.step;

public class LoginTests extends BaseTest {

    @Test
    public void loginUITest() {

        step("Open login page", () ->
            open("/login"));
        step("Fill the login field", () ->
                $("#Email").setValue(login));
        step("Fill the password field", () ->
                $("#Password").setValue(password));
        step("Click on the login button", () ->
                $("input.button-1.login-button").click());
        step("Assert of login", () ->
                $(".account").shouldHave(text(login)));
        }

    @Test
    public void loginAPITest() {

        step("Get cookie", () -> {

            String authKey = "NOPCOMMERCE.AUTH";
            String authValue = given()
                    .contentType("application/x-www-form-urlencoded")
                    .formParam("Email", login)
                    .formParam("Password", password)
                    .when()
                    .post("/login")
                    .then()
                    .log().all()
                    .statusCode(302)
                    .extract().cookie(authKey);

            open("/Content/jquery-ui-themes/smoothness/images/ui-bg_flat_75_ffffff_40x100.png");
            Cookie authCookie = new Cookie(authKey,authValue);
            getWebDriver().manage().addCookie(authCookie);
                });

        step("Open login page", () ->
                open(""));

        step("Assert of login", () ->
                $(".account").shouldHave(text(login)));
    }
}
