package owner_test;

import data.TestData;
import io.qameta.allure.Feature;
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

    @Tag("local_owner")
    @Test
    @DisplayName("OWNER Автотест с POM для DEMOQA")
    public void minimalDataTestLocal() {

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

    @Tag("remote_owner")
    @Test
    @DisplayName("OWNER Автотест с POM для DEMOQA")
    public void minimalDataTestRemote() {

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
}
