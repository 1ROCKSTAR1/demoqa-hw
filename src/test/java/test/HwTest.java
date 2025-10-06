package test;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Selenide.*;

public class HwTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1200";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        Configuration.timeout = 5000;
    }

    @Test
    void fillFormTest() {
        open("/automation-practice-form");
        $x("//input[@placeholder='First Name']").setValue("Tom");
        $x("//input[@placeholder='Last Name']").setValue("Adams");
        $x("//*[@id='userEmail']").setValue("tomadams99@gmail.com");
        $x("//label[contains(text(),'Male')]").click();
        $x("//*[@id='userNumber']").setValue("99999999");
        $x("//input[@id='dateOfBirthInput']").click();
        $x("//div[@aria-label='Choose Sunday, October 5th, 2025']").click();
        $("#subjectsInput").setValue("Math, Physics");
        $x("//label[contains(text(),'Sports')]").click();
        $("#uploadPicture").uploadFile(new File("C:/Users/AVoronin/Desktop/mif10.jpg"));
        $x("//textarea[@placeholder='Current Address']").setValue("Jakarta");
        executeJavaScript("window.scrollBy(0, 300);");
        $x("//div[contains(text(),'Select State')]").click();
        $x("//*[@id='react-select-3-option-3']").click();
        $x("//div[contains(text(),'Select City')]").click();
        $x("//*[@id='react-select-4-option-0']").click();
        $("#submit").click();
    }
}
