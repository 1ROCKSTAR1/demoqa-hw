package owner_test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

public class BaseTest {

    protected static final TestConfig CONFIG = TestConfig.create();

    @BeforeAll
    static void setupEnvironment() {

        Configuration.pageLoadStrategy = "eager";
        Configuration.timeout = 10000;
        Configuration.pageLoadTimeout = 10000;

        if (CONFIG.isRemote()) {
            Configuration.remote = CONFIG.remoteUrl();
            Configuration.browserSize = CONFIG.browserSize();

            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("browserName", "firefox");
            capabilities.setCapability("browserVersion", CONFIG.browserVersion());
            capabilities.setCapability("selenoid:options",
                    Map.<String, Object>of(
                            "enableVNC", CONFIG.vncEnable(),
                            "enableVideo", CONFIG.videoEnable()
                    )
            );

            Configuration.browserCapabilities = capabilities;
        }

        Configuration.browser = CONFIG.browserName();
        Configuration.baseUrl = CONFIG.url();
        Configuration.browserSize = CONFIG.browserSize();
        Configuration.browserVersion = CONFIG.browserVersion();
    }

    @BeforeEach
    void beforeSingle() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }
}

