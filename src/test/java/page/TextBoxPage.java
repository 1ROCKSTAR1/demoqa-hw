package page;

import com.codeborne.selenide.SelenideElement;
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


    public TextBoxPage navigateToTheForm() {
        open("/text-box");
        executeJavaScript("$('footer').remove();");
        executeJavaScript("$('#fixedban').remove();");
        return this;
    }

    public TextBoxPage fillFullName() {
        fullNameField.setValue(fullName);
        return this;
    }

    public TextBoxPage fillEmailField() {
        emailField.setValue(email);
        return this;
    }

    public TextBoxPage fillCurrentAddress() {
        currentAddressField.setValue(currentAddress);
        return this;
    }

    public TextBoxPage fillPermanentAddress() {
        permanentAddressField.setValue(permanentAddress);
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

    public TextBoxPage checkFullNameField() {
        $("#output").shouldHave(text(fullName));
        return this;
    }

    public TextBoxPage checkEmailField() {
        $("#output").shouldHave(text(email));
        return this;
    }

    public TextBoxPage checkCurrentsAddressField() {
        $("#output").shouldHave(text(currentAddress));
        return this;
    }

    public TextBoxPage checkPermanentAddressField() {
        $("#output").shouldHave(text(permanentAddress));
        return this;
    }
}
