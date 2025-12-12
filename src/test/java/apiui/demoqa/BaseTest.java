package apiui.demoqa;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

public abstract class BaseTest {

    protected static RequestSpecification requestSpecification;

    String login = "Tom99";
    String password = "P@ssword1";

    @BeforeAll
    static void setUp() {
        Configuration.baseUrl = "https://demoqa.com";
        RestAssured.baseURI = "https://demoqa.com";
        Configuration.browserSize = "1920x1200";
        Configuration.pageLoadStrategy = "eager";
        Configuration.timeout = 5000;

        requestSpecification = new RequestSpecBuilder()
                .setBaseUri("https://demoqa.com")
                .setContentType(ContentType.JSON)
                .build();
    }

    @AfterEach
    public void tearDown() {
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
        Selenide.closeWebDriver();
    }
}
