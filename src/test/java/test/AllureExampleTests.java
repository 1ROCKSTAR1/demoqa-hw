package test;

import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.logevents.SelenideLogger.step;

@Feature("AllureExample")
public class AllureExampleTests extends BaseTest {

    @DisplayName("Чистый тест с Listener")
    @Test
    public void severalEmailDomensTest() {
        open("/text-box");
        executeJavaScript("$('footer').remove();");
        executeJavaScript("$('#fixedban').remove();");
        $("#userName").setValue("Tom Adams");
        $("#userEmail").setValue("alex22@ya.com");
        $("#currentAddress").setValue("Moscow");
        $("#permanentAddress").setValue("Miami");
        $("#submit").click();
        $("#output").shouldHave(text("alex22@ya.com"));
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
