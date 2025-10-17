package test;

import base.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import page.TextBoxPage;

public class TextBoxTests extends BaseTest {

    TextBoxPage textBoxPage = new TextBoxPage();

    @Test
    @DisplayName("Переписанный на POM автотест для TextBox")
    void fillFormTest() {

        textBoxPage
                .navigateToTheForm()
                .fillFullName()
                .fillEmailField()
                .fillCurrentAddress()
                .fillPermanentAddress()
                .clickOnSubmit();
        textBoxPage
                .checkFullNameField()
                .checkEmailField()
                .checkCurrentsAddressField()
                .checkPermanentAddressField();
    }
}
