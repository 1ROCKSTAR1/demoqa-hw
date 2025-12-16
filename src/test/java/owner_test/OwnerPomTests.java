package owner_test;

import data.TestData;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.MainLoginRegPage;
import pages.components.ModalFinishWindowComponent;

@Tag("Owner test")
@Feature("RegTest with owner")
public class OwnerPomTests extends BaseTest {

    MainLoginRegPage mainLoginRegPage = new MainLoginRegPage();

    ModalFinishWindowComponent component = new ModalFinishWindowComponent();

    TestData testData = new TestData();

    @Test
    @DisplayName("Автотест для DEMOQA с POM + faker + data generation")
    public void firstPositiveFormTest() {

        mainLoginRegPage
                .navigateToTheForm()
                .fillFirstName(testData.firstName)
                .fillLastName(testData.lastName)
                .fillEmail(testData.email)
                .pickGender(testData.selectedGender)
                .fillPhoneNumber(testData.phoneNumber)
                .setBirthDate(testData.day, testData.month, testData.year)
                .setSubject(testData.subject)
                .setHobby(testData.selectedHobby)
                .uploadPicture(testData.selectedPicture)
                .setCurrentAddress(testData.address)
                .setState(testData.selectedState)
                .setCity(testData.selectedCity)
                .clickOnSubmit();
        component
                .checkModalFinishWindow("Student Name", testData.firstName + " " + testData.lastName)
                .checkModalFinishWindow("Student Email", testData.email)
                .checkModalFinishWindow("Gender", testData.selectedGender)
                .checkModalFinishWindow("Mobile", testData.phoneNumber)
                .checkModalFinishWindow("Date of Birth", testData.day + " " + testData.month + "," + testData.year)
                .checkModalFinishWindow("Subjects", testData.subject)
                .checkModalFinishWindow("Hobbies", testData.selectedHobby)
                .checkModalFinishWindow("Picture", testData.selectedPicture)
                .checkModalFinishWindow("Address", testData.address)
                .checkModalFinishWindow("State and City", "State and City" + " " + testData.selectedState + " " + testData.selectedCity);
    }

    @Test
    @DisplayName("Автотест с POM для DEMOQA с минимальным набором данных + faker")
    public void minimalDataTest() {

        mainLoginRegPage
                .navigateToTheForm()
                .fillFirstName(testData.firstName)
                .fillLastName(testData.lastName)
                .pickGender(testData.selectedGender)
                .fillPhoneNumber(testData.phoneNumber)
                .clickOnSubmit();
        component
                .checkModalFinishWindow("Student Name", testData.firstName + " " + testData.lastName)
                .checkModalFinishWindow("Gender", testData.selectedGender)
                .checkModalFinishWindow("Mobile", testData.phoneNumber);
    }

    @Test
    @DisplayName("Негативный автотест с POM для DEMOQA с незаполненным полем Last Name + faker")
    public void negativeTest() {

        mainLoginRegPage
                .navigateToTheForm()
                .fillFirstName(testData.firstName)
                .pickGender(testData.selectedGender)
                .fillPhoneNumber(testData.phoneNumber)
                .clickOnSubmit();
        component
                .checkModalFinishWindow("Student Name", testData.firstName + " " + testData.lastName)
                .checkModalFinishWindow("Gender", testData.selectedGender)
                .checkModalFinishWindow("Mobile", testData.phoneNumber);
    }

    @Test
    @DisplayName("Проверка конфигурации Owner")
    void testConfig() {
        System.out.println("Конфигурация:");
        System.out.println("Browser: " + CONFIG.browser());
        System.out.println("Browser Size: " + CONFIG.browserSize());
        System.out.println("Base URL: " + CONFIG.baseUrl());

        Assertions.assertEquals("chrome", CONFIG.browser());
        Assertions.assertEquals("1920x1080", CONFIG.browserSize());
        Assertions.assertEquals("https://demoqa.com", CONFIG.baseUrl());
    }
}
