package test;

import base.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import page.MainLoginRegPage;

public class PomTests extends BaseTest {

    @Test
    @DisplayName("Первый автотест с POM для DEMOQA")
    public void firstPositiveFormTest() {

        MainLoginRegPage mainLoginRegPage = new MainLoginRegPage()
                .navigateToTheForm()
                .fillFirstName()
                .fillLastName()
                .fillEmail()
                .pickGender()
                .fillPhoneNumber()
                .setBirthDate()
                .setSubject()
                .setHobby()
                .uploadPicture()
                .setCurrentAddress()
                .scrollToLocationFields()
                .clickOnSelectState()
                .chooseFourthStateOption()
                .clickOnSelectCity()
                .chooseFirstCityOption()
                .clickOnSubmit()
                .checkAssertFirstAndLastName()
                .checkAssertEmail()
                .checkAssertGender()
                .checkAssertPhoneNumber()
                .checkAssertDateOfBirth()
                .checkAssertSubjects()
                .checkAssertHobbies()
                .checkAssertPucture()
                .checkAssertAddress()
                .checkAssertStateAndCity();
    }

    @Test
    @DisplayName("Автотест с POM для DEMOQA с минимальным набором данных")
    public void minimalDataTest() {
        MainLoginRegPage mainLoginRegPage = new MainLoginRegPage()
                .navigateToTheForm()
                .fillFirstName()
                .fillLastName()
                .pickGender()
                .fillPhoneNumber()
                .clickOnSubmit()
                .checkAssertFirstAndLastName()
                .checkAssertGender()
                .checkAssertPhoneNumber();
    }

    @Test
    @DisplayName("Негативный автотест с POM для DEMOQA с незаполненным полем Last Name")
    public void negativeTest() {
        MainLoginRegPage mainLoginRegPage = new MainLoginRegPage()
                .navigateToTheForm()
                .fillFirstName()
                .pickGender()
                .fillPhoneNumber()
                .clickOnSubmit()
                .checkAssertFirstAndLastName()
                .checkAssertGender()
                .checkAssertPhoneNumber();
    }
}
