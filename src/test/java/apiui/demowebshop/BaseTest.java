package apiui.demowebshop;

import com.codeborne.selenide.Configuration;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {

    String login = "elrockstar25@gmail.com";
    String password = "p@ssw0rd1";

    @BeforeAll
    static void setUp() {
        Configuration.baseUrl = "https://demowebshop.tricentis.com/";
        Configuration.browserSize = "1920x1200";
        Configuration.pageLoadStrategy = "eager";
        Configuration.timeout = 5000;
        RestAssured.baseURI = "https://demowebshop.tricentis.com";
    }
}
