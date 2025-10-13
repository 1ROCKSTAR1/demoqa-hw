package test;

import base.BaseTest;
import org.junit.jupiter.api.Test;
import page.TextBoxPage;

public class TextBoxTests extends BaseTest {

    @Test
    void fillFormTest() {

        TextBoxPage textBoxPage = new TextBoxPage()
                .navigateToTheForm()
                .fillFullName()
                .fillEmailField()
                .fillCurrentAddress()
                .fillPermanentAddress()
                .clickOnSubmit()
                .checkAssertFinalField("name", "Tom Adams")
                .checkAssertFinalField("email","tomadams99@ya.com")
                .checkAssertFinalField("currentAddress","Jakarta")
                .checkAssertFinalField("permanentAddress","Rio");
    }
}
