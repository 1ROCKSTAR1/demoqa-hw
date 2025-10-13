package page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class TextBoxPage {

    private final SelenideElement fullNameField = $("#userName"),
                emailField = $("#userEmail"),
                currentAddressField = $("#currentAddress"),
                permanentAddressField = $("#permanentAddress"),
                submitButton = $("#submit");

    public TextBoxPage navigateToTheForm() {
        open("/text-box");
        executeJavaScript("$('footer').remove();");
        executeJavaScript("$('#fixedban').remove();");
        return this;
    }

    public TextBoxPage fillFullName() {
        fullNameField.setValue("Tom Adams");
        return this;
    }

    public TextBoxPage fillEmailField() {
        emailField.setValue("tomadams99@ya.com");
        return this;
    }

    public TextBoxPage fillCurrentAddress() {
        currentAddressField.setValue("Jakarta");
        return this;
    }

    public TextBoxPage fillPermanentAddress() {
        permanentAddressField.setValue("Rio");
        return this;
    }

    public TextBoxPage clickOnSubmit() {
        submitButton.click();
        return this;
    }

    public TextBoxPage checkAssertFinalField(String key, String value) {
        $("#output").$(By.id(key)).shouldHave(text(value));
        return this;
    }
}
