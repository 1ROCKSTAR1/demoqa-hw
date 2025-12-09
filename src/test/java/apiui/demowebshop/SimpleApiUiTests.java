package apiui.demowebshop;

import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import java.util.concurrent.atomic.AtomicReference;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.codeborne.selenide.logevents.SelenideLogger.step;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.Is.is;

public class SimpleApiUiTests extends BaseTest {

    @Test
    @DisplayName("Обычный UI тест с авторизацией")
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
    @DisplayName("MIXED тест API+UI авторизация")
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

    @Test
    @DisplayName("API авторизация + API добавление товара в корзину")
    public void addItemToCart() {

        String authKey = "NOPCOMMERCE.AUTH";
        AtomicReference<String> authValue = new AtomicReference<>("");

        step("Get cookie", () -> {

            authValue.set(given()
                    .contentType("application/x-www-form-urlencoded")
                    .formParam("Email", login)
                    .formParam("Password", password)
                    .when()
                    .post("/login")
                    .then()
                    .log().all()
                    .statusCode(302)
                    .extract().cookie(authKey));
                });

        step("Create a post", () -> {

        String buyData =
                "product_attribute_72_5_18=65"+
                "&product_attribute_72_6_19=91"+
                "&product_attribute_72_3_20=58"+
                "&addtocart_72.EnteredQuantity=1";

        given()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .cookie(authKey,authValue.get())
                .body(buyData)
                .when()
                .post("/addproducttocart/details/72/1")
                .then()
                .log().all()
                .statusCode(200)
                .body("success", is(true))
                .body("message", containsString("The product has been added to your"));
        });
    }
}
