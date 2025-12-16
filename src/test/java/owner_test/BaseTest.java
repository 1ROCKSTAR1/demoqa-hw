package owner_test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class BaseTest {

    protected static final TestConfig CONFIG = ConfigFactory.create(TestConfig.class);

    @BeforeAll
    static void setupEnvironment() {

        Configuration.browser = CONFIG.browser();
        Configuration.browserSize = CONFIG.browserSize();
        Configuration.baseUrl = CONFIG.baseUrl();

        Configuration.pageLoadStrategy = "eager";
        Configuration.timeout = 5000;
    }

    @BeforeEach
    void beforeSingle() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }
}

