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

        Configuration.browser = CONFIG.browserName();
        Configuration.browserSize = CONFIG.browserSize();
        Configuration.baseUrl = "https://demoqa.com";


        if (CONFIG.isRemote()) {
            Configuration.remote = CONFIG.remoteUrl();


            Configuration.browserCapabilities.setCapability("enableVNC", CONFIG.vncEnable());
            Configuration.browserCapabilities.setCapability("enableVideo", CONFIG.videoEnable());
            Configuration.browserCapabilities.setCapability("browserVersion", CONFIG.browserVersion());


            Configuration.browserCapabilities.setCapability("selenoid:options",
                    java.util.Map.<String, Object>of(
                            "enableVNC", CONFIG.vncEnable(),
                            "enableVideo", CONFIG.videoEnable()
                    )
            );
        } else {
            Configuration.browserVersion = CONFIG.browserVersion();
        }

        Configuration.pageLoadStrategy = "eager";
        Configuration.timeout = 10000;
        Configuration.pageLoadTimeout = 10000;
    }

    @BeforeEach
    void beforeSingle() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }
}

