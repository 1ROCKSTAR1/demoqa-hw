package page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import page.component.CalendarComponent;
import page.component.ModalFinishWindowComponent;
import utils.RandomStringUtil;

import java.time.LocalDate;
import java.util.Map;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static utils.RandomStringUtil.*;

public class MainLoginRegPage {

    private final SelenideElement firstNameField = $("#firstName"),
                                  lastNameField = $("#lastName"),
                                  emailField = $("#userEmail"),
                                  phoneNumberField = $("#userNumber"),
                                  dateOfBirthField = $("#dateOfBirthInput"),
                                  subjectsField = $("#subjectsInput"),
                                  uploadPictureArea = $("#uploadPicture"),
                                  currentAddressField = $("#currentAddress"),
                                  submitButton = $("#submit");

    private final ElementsCollection genderRadioButtons = $$("[for^=gender-radio]"),
                                     hobbiesCheckboxes = $$("label[for^='hobbies-checkbox-']");

    String firstName = getRandomFirstName();
    String lastName = getRandomLastName();
    String email = getRandomEmail();
    String phoneNumber = getRandomPhoneNumber();
    String subject = getRandomSubjects();
    String address = getRandomAddress();
    String selectedGender;
    String selectedHobby;
    String selectedState;
    String selectedCity;
    String selectedBirthDate;
    String selectedPicture;

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
        int randomIndex = getRandomIndex(3);//;
        SelenideElement selectedButton = genderRadioButtons.get(randomIndex);
        selectedButton.click();

        selectedGender = selectedButton.getText();
        return this;
    }

    public MainLoginRegPage fillPhoneNumber() {
        phoneNumberField.setValue(phoneNumber);
        return this;
    }

    public MainLoginRegPage setBirthDate() {
        dateOfBirthField.click();
        CalendarComponent calendar = new CalendarComponent();
        LocalDate randomDate = calendar.setRandomDate(); // получаем дату
        this.selectedBirthDate = CalendarComponent.formatDate(randomDate); // сохраняем в странице
        return this;
    }

    public MainLoginRegPage setSubject() {
        subjectsField.setValue(subject).pressEnter();
        return this;
    }

    public MainLoginRegPage setHobby() {
        int randomIndex = getRandomIndex(3);
        SelenideElement selectedCheckbox = hobbiesCheckboxes.get(randomIndex);
        selectedCheckbox.click();
        selectedHobby = selectedCheckbox.getText();
        return this;
    }

    public MainLoginRegPage uploadPicture() {
        String randomPicture = RandomStringUtil.getRandomPicture();
        uploadPictureArea.uploadFromClasspath(randomPicture);
        this.selectedPicture = randomPicture;
        return this;
    }

    public MainLoginRegPage setCurrentAddress() {
        currentAddressField.setValue(address);
        return this;
    }

    public MainLoginRegPage selectRandomStateAndCity() {

        Map.Entry<String, String> stateCity = RandomStringUtil.getRandomStateAndCity();
        this.selectedState = stateCity.getKey();
        this.selectedCity = stateCity.getValue();

        $(byText("Select State")).scrollTo().click();
        $(byText(selectedState)).click();

        $(byText("Select City")).click();
        $(byText(selectedCity)).click();

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
        finishWindowComponent.checkModalFinishWindow("Gender",selectedGender);
        return this;
    }

    public MainLoginRegPage checkAssertPhoneNumber() {
        ModalFinishWindowComponent finishWindowComponent = new ModalFinishWindowComponent();
        finishWindowComponent.checkModalFinishWindow("Mobile",phoneNumber);
        return this;
    }

    public MainLoginRegPage checkAssertDateOfBirth() {
        ModalFinishWindowComponent finishWindowComponent = new ModalFinishWindowComponent();
        finishWindowComponent.checkModalFinishWindow("Date of Birth", selectedBirthDate);
        return this;
    }

    public MainLoginRegPage checkAssertSubjects() {
        ModalFinishWindowComponent finishWindowComponent = new ModalFinishWindowComponent();
        finishWindowComponent.checkModalFinishWindow("Subjects",subject);
        return this;
    }

    public MainLoginRegPage checkAssertHobbies() {
        ModalFinishWindowComponent finishWindowComponent = new ModalFinishWindowComponent();
        finishWindowComponent.checkModalFinishWindow("Hobbies",selectedHobby);
        return this;
    }

    public MainLoginRegPage checkAssertPicture() {
        ModalFinishWindowComponent finishWindowComponent = new ModalFinishWindowComponent();
        finishWindowComponent.checkModalFinishWindow("Picture",selectedPicture);
        return this;
    }

    public MainLoginRegPage checkAssertAddress() {
        ModalFinishWindowComponent finishWindowComponent = new ModalFinishWindowComponent();
        finishWindowComponent.checkModalFinishWindow("Address",address);
        return this;
    }

    public MainLoginRegPage checkAssertStateAndCity() {
        ModalFinishWindowComponent finishWindowComponent = new ModalFinishWindowComponent();
        finishWindowComponent.checkModalFinishWindow("State and City",selectedState + " " + selectedCity);
        return this;
    }
}
