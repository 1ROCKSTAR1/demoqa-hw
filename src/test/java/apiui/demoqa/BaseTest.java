package apiui.demoqa;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

public abstract class BaseTest {

    protected static RequestSpecification requestSpecification;

    String login = "Tom99";
    String password = "P@ssword1";

    @BeforeAll
    static void setUp() {
        RestAssured.baseURI = "https://demoqa.com";
        Configuration.baseUrl = System.getProperty("baseUrl","https://demoqa.com");
        Configuration.browser = System.getProperty("browser","chrome");
        Configuration.browserSize = System.getProperty("resolution","1920x1080");
        Configuration.browserVersion = System.getProperty("browserVersion","127.0");
        Configuration.timeout = 10000;
        Configuration.remote = System.getProperty("remoteDriverUrl","https://user1:1234@selenoid.autotests.cloud/wd/hub");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;

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
