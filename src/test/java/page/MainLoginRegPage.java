package page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import page.component.CalendarComponent;
import page.component.ModalFinishWindowComponent;
import utils.RandomStringUtil;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static utils.RandomStringUtil.*;

public class MainLoginRegPage {

    private final SelenideElement firstNameField = $("#firstName"),
                                  lastNameField = $("#lastName"),
                                  emailField = $("#userEmail"),
                                  maleGenderRadioButton = $("[for=gender-radio-1]"),
                                  phoneNumberField = $("#userNumber"),
                                  dateOfBirthField = $("#dateOfBirthInput"),
                                  subjectsField = $("#subjectsInput"),
                                  hobbySportsRadioButton = $("label[for='hobbies-checkbox-1']"),
                                  uploadPictureArea = $("#uploadPicture"),
                                  currentAddressField = $("#currentAddress"),
                                  selectStateField = $(byText("Select State")),
                                  rajasthanOption = $("#react-select-3-option-3"),
                                  selectCityField = $(byText("Select City")),
                                  jaipurOption = $("#react-select-4-option-0"),
                                  submitButton = $("#submit");

    private final ElementsCollection genderRadioButtons =
            $$("[for^=gender-radio]");

    String firstName = getRandomFirstName();
    String lastName = getRandomLastName();
    String email = getRandomEmail();
    String phoneNumber = getRandomPhoneNumber();
    String subject = getRandomSubjects();
    String address = getRandomAddress();

    public MainLoginRegPage navigateToTheForm() {
        open("/automation-practice-form");
        executeJavaScript("$('footer').remove();");
        executeJavaScript("$('#fixedban').remove();");
        return this;
    }

    public MainLoginRegPage fillFirstName() {
        firstNameField.setValue(firstName);
        return this;
    }

    public MainLoginRegPage fillLastName() {
        lastNameField.setValue(lastName);
        return this;
    }

    public MainLoginRegPage fillEmail() {
        emailField.setValue(email);
        return this;
    }

    public MainLoginRegPage pickGender() {
        int randomIndex = RandomStringUtil.getRandomIndex(genderRadioButtons.size());
        genderRadioButtons.get(randomIndex).click();
        return this;
    }

    public MainLoginRegPage fillPhoneNumber() {
        phoneNumberField.setValue(phoneNumber);
        return this;
    }

    public MainLoginRegPage setBirthDate() {
        CalendarComponent component = new CalendarComponent();
        dateOfBirthField.click();
        component.setDate();
        return this;
    }

    public MainLoginRegPage setSubject() {
        subjectsField.setValue(subject).pressEnter();
        return this;
    }

    public MainLoginRegPage setHobby() {
        hobbySportsRadioButton.click();
        return this;
    }

    public MainLoginRegPage uploadPicture() {
        uploadPictureArea.uploadFromClasspath("mif10.jpg");
        return this;
    }

    public MainLoginRegPage setCurrentAddress() {
        currentAddressField.setValue(address);
        return this;
    }

    public MainLoginRegPage scrollToLocationFieldsAndClickOnState() {
        $(byText("Select State")).scrollTo().click();
        return this;
    }

    public MainLoginRegPage chooseFourthStateOption() {
        rajasthanOption.click();
        return this;
    }

    public MainLoginRegPage clickOnSelectCity() {
        selectCityField.click();
        return this;
    }

    public MainLoginRegPage chooseFirstCityOption() {
        jaipurOption.click();
        return this;
    }

    public MainLoginRegPage clickOnSubmit() {
        submitButton.click();
        return this;
    }

    public MainLoginRegPage checkAssertFirstAndLastName() {
        ModalFinishWindowComponent finishWindowComponent = new ModalFinishWindowComponent();
        finishWindowComponent.checkModalFinishWindow("Student Name",firstName + " " + lastName);
        return this;
    }

    public MainLoginRegPage checkAssertEmail() {
        ModalFinishWindowComponent finishWindowComponent = new ModalFinishWindowComponent();
        finishWindowComponent.checkModalFinishWindow("Student Email",email);
        return this;
    }

    public MainLoginRegPage checkAssertGender() {
        ModalFinishWindowComponent finishWindowComponent = new ModalFinishWindowComponent();
        finishWindowComponent.checkModalFinishWindow("Gender","Male");
        return this;
    }

    public MainLoginRegPage checkAssertPhoneNumber() {
        ModalFinishWindowComponent finishWindowComponent = new ModalFinishWindowComponent();
        finishWindowComponent.checkModalFinishWindow("Mobile",phoneNumber);
        return this;
    }

    public MainLoginRegPage checkAssertDateOfBirth() {
        ModalFinishWindowComponent finishWindowComponent = new ModalFinishWindowComponent();
        finishWindowComponent.checkModalFinishWindow("Date of Birth","05 October,2025");
        return this;
    }

    public MainLoginRegPage checkAssertSubjects() {
        ModalFinishWindowComponent finishWindowComponent = new ModalFinishWindowComponent();
        finishWindowComponent.checkModalFinishWindow("Subjects",subject);
        return this;
    }

    public MainLoginRegPage checkAssertHobbies() {
        ModalFinishWindowComponent finishWindowComponent = new ModalFinishWindowComponent();
        finishWindowComponent.checkModalFinishWindow("Hobbies","Sports");
        return this;
    }

    public MainLoginRegPage checkAssertPicture() {
        ModalFinishWindowComponent finishWindowComponent = new ModalFinishWindowComponent();
        finishWindowComponent.checkModalFinishWindow("Picture","mif10.jpg");
        return this;
    }

    public MainLoginRegPage checkAssertAddress() {
        ModalFinishWindowComponent finishWindowComponent = new ModalFinishWindowComponent();
        finishWindowComponent.checkModalFinishWindow("Address",address);
        return this;
    }

    public MainLoginRegPage checkAssertStateAndCity() {
        ModalFinishWindowComponent finishWindowComponent = new ModalFinishWindowComponent();
        finishWindowComponent.checkModalFinishWindow("State and City","Rajasthan Jaipur");
        return this;
    }
}
