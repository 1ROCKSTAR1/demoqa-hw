package owner_test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

public class BaseTest {

    private static final TestConfig config =
            ConfigFactory.create(TestConfig.class, System.getProperties());



    @BeforeAll
    static void setupEnvironment() {

        Configuration.pageLoadStrategy = "eager";
        Configuration.timeout = 10000;
        Configuration.pageLoadTimeout = 10000;

        if (config.isRemote()) {
            Configuration.remote = config.remoteUrl();
            Configuration.browserSize = config.browserSize();
            Configuration.browser = config.browserName();
            Configuration.browserVersion = config.browserVersion();


            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("selenoid:options",
                    Map.<String, Object>of(
                            "enableVNC", config.vncEnable(),
                            "enableVideo", config.videoEnable()
                    )
            );

            Configuration.browserCapabilities = capabilities;
        }

        Configuration.browser = config.browserName();
        Configuration.baseUrl = config.url();
        Configuration.browserSize = config.browserSize();
        Configuration.browserVersion = config.browserVersion();
    }

    @BeforeEach
    void beforeSingle() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
    }
}

