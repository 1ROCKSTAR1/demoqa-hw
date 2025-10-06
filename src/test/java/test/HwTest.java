package test;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
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
        $x("//*[@id='userNumber']").setValue("1234567899");
        $x("//input[@id='dateOfBirthInput']").click();
        $x("//div[@aria-label='Choose Sunday, October 5th, 2025']").click();
        $("#subjectsInput").setValue("Chemistry").pressEnter();
        $x("//label[contains(text(),'Sports')]").click();
        $("#uploadPicture").uploadFile(new File("C:/Users/AVoronin/Desktop/mif10.jpg"));
        $x("//textarea[@placeholder='Current Address']").setValue("Jakarta");
        executeJavaScript("window.scrollBy(0, 300);");
        $x("//div[contains(text(),'Select State')]").click();
        $x("//*[@id='react-select-3-option-3']").click();
        $x("//div[contains(text(),'Select City')]").click();
        $x("//*[@id='react-select-4-option-0']").click();
        $("#submit").click();

        $x("//tr[td='Student Name']/td[2]").shouldHave(text("Tom"));
        $x("//tr[td='Student Email']/td[2]").shouldHave(text("tomadams99@gmail.com"));
        $x("//tr[td='Gender']/td[2]").shouldHave(text("Male"));
        $x("//tr[td='Mobile']/td[2]").shouldHave(text("1234567899"));
        $x("//tr[td='Date of Birth']/td[2]").shouldHave(text("05 October,2025"));
        $x("//tr[td='Subjects']/td[2]").shouldHave(text("Chemistry"));
        $x("//tr[td='Hobbies']/td[2]").shouldHave(text("Sports"));
        $x("//tr[td='Picture']/td[2]").shouldHave(text("mif10.jpg"));
        $x("//tr[td='Address']/td[2]").shouldHave(text("Jakarta"));
        $x("//tr[td='State and City']/td[2]").shouldHave(text("Rajasthan Jaipur"));
    }
}
