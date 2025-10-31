package test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.logevents.SelenideLogger.step;

public class SingleTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 10000;
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
    }

    @DisplayName("Тест с лямбда шагами")
    @Test
    public void severalEmailDomens2Test() {
        step("Переход на страницу", () -> {
            open("/text-box");
        });

        step("Скрытие рекламы", () -> {
            executeJavaScript("$('footer').remove();");
            executeJavaScript("$('#fixedban').remove();");
        });

        step("Заполнение полного имени", () -> {
            $("#userName").setValue("Tom Adams");
        });

        step("Заполнение имейла", () -> {
            $("#userEmail").setValue("alex22@ya.com");
        });

        step("Заполнение текущего адреса", () -> {
            $("#currentAddress").setValue("Moscow");
        });

        step("Заполнение временного адреса", () -> {
            $("#permanentAddress").setValue("Miami");
        });

        step("Клик по кнопке Submit", () -> {
            $("#submit").click();
        });

        step("Проверка поля имейл", () -> {
            $("#output").shouldHave(text("alex22@ya.com"));
        });
    }
}
