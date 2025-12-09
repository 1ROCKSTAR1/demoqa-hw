package apiui.demoqa;

import com.codeborne.selenide.Configuration;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {

    String login = "Tom99";
    String password = "P@ssword1";

    @BeforeAll
    static void setUp() {
        Configuration.baseUrl = "https://demoqa.com";
        RestAssured.baseURI = "https://demoqa.com";
        Configuration.browserSize = "1920x1200";
        Configuration.pageLoadStrategy = "eager";
        Configuration.timeout = 5000;
    }
}
