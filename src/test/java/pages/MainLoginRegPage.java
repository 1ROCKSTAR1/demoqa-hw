package pages;

import data.TestData;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.github.javafaker.Faker;
import io.qameta.allure.Step;
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

    @Step("Переход на страницу")
    public MainLoginRegPage navigateToTheForm() {
        open("/automation-practice-form");
        executeJavaScript("$('footer').remove();");
        executeJavaScript("$('#fixedban').remove();");
        return this;
    }

    @Step("Заполнение имени")
    public MainLoginRegPage fillFirstName(String firstName) {
        firstNameField.setValue(firstName);
        return this;
    }

    @Step("Заполнение фамилии")
    public MainLoginRegPage fillLastName(String lastName) {
        lastNameField.setValue(lastName);
        return this;
    }

    @Step("Заполнение имейла")
    public MainLoginRegPage fillEmail(String email) {
        emailField.setValue(email);
        return this;
    }

    @Step("Выбор гендера")
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

    @Step("Заполнение тел.номера")
    public MainLoginRegPage fillPhoneNumber(String phoneNumber) {
        phoneNumberField.setValue(phoneNumber);
        return this;
    }

    @Step("Установка даты рождения")
    public MainLoginRegPage setBirthDate(String day, String month, String year) {
        dateOfBirthField.click();
        CalendarComponent component = new CalendarComponent();
        component.setDate(day, month, year);
        return this;
    }

    @Step("Установка предмета")
    public MainLoginRegPage setSubject(String subject) {
        subjectsField.setValue(subject).pressEnter();
        String selectedSubject = subject;
        return this;
    }

    @Step("Установка хобби")
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

    @Step("Загрузка картинки")
    public MainLoginRegPage uploadPicture(String selectedPicture) {
        uploadPictureArea.uploadFromClasspath(selectedPicture);
        return this;
    }

    @Step("Установка текущего адреса")
    public MainLoginRegPage setCurrentAddress(String address) {
        currentAddressField.setValue(address);
        return this;
    }

    @Step("Установка штата")
    public MainLoginRegPage setState(String selectedState) {
        selectStateDropDown.scrollTo().click();
        $(byText(selectedState)).click();
        return this;
    }

    @Step("Установка города")
    public MainLoginRegPage setCity(String selectedCity) {
        selectCityDropDown.click();
        $(byText(selectedCity)).click();
        return this;
    }

    @Step("Клик по кнопке Submit")
    public MainLoginRegPage clickOnSubmit() {
        submitButton.click();
        return this;
    }
}