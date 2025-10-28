package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static utils.RandomStringUtil.*;

import static com.codeborne.selenide.Selenide.*;

public class TextBoxPage {

    private final SelenideElement fullNameField = $("#userName"),
                emailField = $("#userEmail"),
                currentAddressField = $("#currentAddress"),
                permanentAddressField = $("#permanentAddress"),
                submitButton = $("#submit");

    public String fullName = getRandomFirstName() + " " + getRandomLastName();
    public String email = getRandomEmail();
    public String currentAddress = getRandomAddress();
    public String permanentAddress = getRandomAddress();

    @Step("Переход на страницу")
    public TextBoxPage navigateToTheForm() {
        open("/text-box");
        executeJavaScript("$('footer').remove();");
        executeJavaScript("$('#fixedban').remove();");
        return this;
    }

    @Step("Заполнение полного имени")
    public TextBoxPage fillFullName() {
        fullNameField.setValue(fullName);
        return this;
    }

    @Step("Заполнение имейла")
    public TextBoxPage fillEmailField() {
        emailField.setValue(email);
        return this;
    }

    @Step("Заполнение текущего адреса")
    public TextBoxPage fillCurrentAddress() {
        currentAddressField.setValue(currentAddress);
        return this;
    }

    @Step("Заполнение временного адреса")
    public TextBoxPage fillPermanentAddress() {
        permanentAddressField.setValue(permanentAddress);
        return this;
    }

    @Step("Клик по кнопке Submmit")
    public TextBoxPage clickOnSubmit() {
        submitButton.click();
        return this;
    }

    @Step("Проверка поля {key} со значением {value}")
    public TextBoxPage checkAssertFinalField(String key, String value) {
        $("#output").$(By.id(key)).shouldHave(text(value));
        return this;
    }

    @Step("Проверка полного имени")
    public TextBoxPage checkFullNameField() {
        $("#output").shouldHave(text(fullName));
        return this;
    }

    @Step("Проверка имейла")
    public TextBoxPage checkEmailField() {
        $("#output").shouldHave(text(email));
        return this;
    }

    @Step("Проверка текущего адреса")
    public TextBoxPage checkCurrentsAddressField() {
        $("#output").shouldHave(text(currentAddress));
        return this;
    }

    @Step("Проверка временного адреса")
    public TextBoxPage checkPermanentAddressField() {
        $("#output").shouldHave(text(permanentAddress));
        return this;
    }
}
