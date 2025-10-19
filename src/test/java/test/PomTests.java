package test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.MainLoginRegPage;

public class PomTests extends BaseTest {

    MainLoginRegPage mainLoginRegPage = new MainLoginRegPage();

    @Test
    @DisplayName("Автотест для DEMOQA с POM + faker")
    public void firstPositiveFormTest() {

        mainLoginRegPage
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
                .selectRandomStateAndCity()
                .clickOnSubmit();
        mainLoginRegPage
                .checkAssertFirstAndLastName()
                .checkAssertEmail()
                .checkAssertGender()
                .checkAssertPhoneNumber()
                .checkAssertDateOfBirth()
                .checkAssertSubjects()
                .checkAssertHobbies()
                .checkAssertPicture()
                .checkAssertAddress()
                .checkAssertStateAndCity();
    }

    @Test
    @DisplayName("Автотест с POM для DEMOQA с минимальным набором данных + faker")
    public void minimalDataTest() {

        mainLoginRegPage
                .navigateToTheForm()
                .fillFirstName()
                .fillLastName()
                .pickGender()
                .fillPhoneNumber()
                .clickOnSubmit();
        mainLoginRegPage
                .checkAssertFirstAndLastName()
                .checkAssertGender()
                .checkAssertPhoneNumber();
    }

    @Test
    @DisplayName("Негативный автотест с POM для DEMOQA с незаполненным полем Last Name + faker")
    public void negativeTest() {

        mainLoginRegPage
                .navigateToTheForm()
                .fillFirstName()
                .pickGender()
                .fillPhoneNumber()
                .clickOnSubmit();
        mainLoginRegPage
                .checkAssertFirstAndLastName()
                .checkAssertGender()
                .checkAssertPhoneNumber();
    }
}
