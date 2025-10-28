package test;

import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.TextBoxPage;

@Feature("TextBox")
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
