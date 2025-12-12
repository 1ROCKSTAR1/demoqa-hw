package apiui.demoqa;

import apiui.models.LoginResp;
import io.qameta.allure.Step;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Cookie;

import static apiui.helpers.TestData.DEFAULT_TITLE;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class ProfilePage {

    @Step("Переход на ресурс и получение кук")
    public ProfilePage getCookies(LoginResp loginResp) {
        open("/favicon.ico");
        getWebDriver().manage().addCookie(new Cookie("userID", loginResp.getUserId()));
        getWebDriver().manage().addCookie(new Cookie("expires", loginResp.getExpires()));
        getWebDriver().manage().addCookie(new Cookie("token",loginResp.getToken()));
        return this;
    }

    @Step("Переход на страницу")
    public ProfilePage navigateToProfile() {
        open("/profile");
        return this;
    }

    @Step("Проверка тайтла добавленной книги")
    public ProfilePage checkingAddedBook(LoginResp loginResp) {
        $("#userName-value").shouldHave(text(loginResp.getUsername()));
        $("a[href='/profile?book=9781449365035']").shouldHave(text(DEFAULT_TITLE));
        return this;
    }

    @Step("Удаление книги и работа с алертом")
    public ProfilePage deleteBookAndfightWithAlert() {
        $("#delete-record-undefined").click();
        $("#closeSmallModal-ok").shouldBe(visible).click();
        Alert alert = switchTo().alert();
        alert.accept();
        return this;
    }

    @Step("Проверка что книга удалена")
    public ProfilePage checkingDeletedBook() {
        $("a[href='/profile?book=9781449365035']").shouldNot(exist);
        return this;
    }
}
