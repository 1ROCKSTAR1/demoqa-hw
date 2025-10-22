package pages;

import Data.TestData;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.github.javafaker.Faker;
import pages.components.CalendarComponent;
import java.util.Locale;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class MainLoginRegPage {

    TestData testData = new TestData();

    Faker faker = new Faker(new Locale("en-AU"));

    private final SelenideElement firstNameField = $("#firstName"),
                                  lastNameField = $("#lastName"),
                                  emailField = $("#userEmail"),
                                  phoneNumberField = $("#userNumber"),
                                  dateOfBirthField = $("#dateOfBirthInput"),
                                  subjectsField = $("#subjectsInput"),
                                  uploadPictureArea = $("#uploadPicture"),
                                  currentAddressField = $("#currentAddress"),
                                  selectStateDropDown = $(byText("Select State")),
                                  selectCityDropDown = $(byText("Select City")),
                                  submitButton = $("#submit");

    private final ElementsCollection genderRadioButtons = $$("[for^=gender-radio]"),
                                     hobbiesCheckboxes = $$("label[for^='hobbies-checkbox-']");

    public MainLoginRegPage navigateToTheForm() {
        open("/automation-practice-form");
        executeJavaScript("$('footer').remove();");
        executeJavaScript("$('#fixedban').remove();");
        return this;
    }

    public MainLoginRegPage fillFirstName(String firstName) {
        firstNameField.setValue(firstName);
        return this;
    }

    public MainLoginRegPage fillLastName(String lastName) {
        lastNameField.setValue(lastName);
        return this;
    }

    public MainLoginRegPage fillEmail(String email) {
        emailField.setValue(email);
        return this;
    }

    public MainLoginRegPage pickGender(String selectedGender) {
        genderRadioButtons
                .asFixedIterable()
                .stream()
                .filter(e -> e.text().equals(selectedGender))
                .findFirst()
                .get()
                .click();
        return this;
    }

    public MainLoginRegPage fillPhoneNumber(String phoneNumber) {
        phoneNumberField.setValue(phoneNumber);
        return this;
    }

    public MainLoginRegPage setBirthDate(String day, String month, String year) {
        dateOfBirthField.click();
        CalendarComponent component = new CalendarComponent();
        component.setDate(day, month, year);
        return this;
    }

    public MainLoginRegPage setSubject(String subject) {
        subjectsField.setValue(subject).pressEnter();
        String selectedSubject = subject;
        return this;
    }

    public MainLoginRegPage setHobby(String selectedHobby) {
        hobbiesCheckboxes
                .asFixedIterable()
                .stream()
                .filter(e -> e.getText().equals(selectedHobby))
                .findFirst()
                .get()
                .click();
        return this;
    }

    public MainLoginRegPage uploadPicture(String selectedPicture) {
        uploadPictureArea.uploadFromClasspath(selectedPicture);
        return this;
    }

    public MainLoginRegPage setCurrentAddress(String address) {
        currentAddressField.setValue(address);
        return this;
    }

    public MainLoginRegPage setState(String selectedState) {
        selectStateDropDown.scrollTo().click();
        $(byText(selectedState)).click();
        return this;
    }

    public MainLoginRegPage setCity(String selectedCity) {
        selectCityDropDown.click();
        $(byText(selectedCity)).click();
        return this;
    }

    public MainLoginRegPage clickOnSubmit() {
        submitButton.click();
        return this;
    }
}