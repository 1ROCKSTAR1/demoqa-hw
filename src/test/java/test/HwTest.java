package test;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class HwTest {

    @BeforeAll
    static void setupEnvironment() {
        Configuration.browserSize = "1920x1200";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        Configuration.timeout = 5000;
    }

    @Test
    void fillFormTest() {
        open("/automation-practice-form");

        $("#firstName").setValue("Tom");
        $("#lastName").setValue("Adams");
        $("#userEmail").setValue("tomadams99@gmail.com");
        $("#genterWrapper label[for='gender-radio-1']").click();
        $("#userNumber").setValue("1234567899");
        $("#dateOfBirthInput").click();
        $("div[aria-label=\"Choose Sunday, October 5th, 2025\"]").click();
        $("#subjectsInput").setValue("Chemistry").pressEnter();
        $("#hobbiesWrapper label[for='hobbies-checkbox-1']").click();
        $("#uploadPicture").uploadFile(new File("src/test/resources/mif10.jpg"));
        $("textarea[placeholder=\"Current Address\"]").setValue("Jakarta");
        executeJavaScript("window.scrollBy(0, 300);");
        $(byText("Select State")).click();
        $("#react-select-3-option-3").click();
        $(byText("Select City")).click();
        $("#react-select-4-option-0").click();
        $("#submit").click();

        $(".modal-body").shouldHave(text("Tom"));
        $(".modal-body").shouldHave(text("tomadams99@gmail.com"));
        $(".modal-body").shouldHave(text("Male"));
        $(".modal-body").shouldHave(text("1234567899"));
        $(".modal-body").shouldHave(text("05 October,2025"));
        $(".modal-body").shouldHave(text("Chemistry"));
        $(".modal-body").shouldHave(text("Sports"));
        $(".modal-body").shouldHave(text("mif10.jpg"));
        $(".modal-body").shouldHave(text("Jakarta"));
        $(".modal-body").shouldHave(text("Rajasthan Jaipur"));
    }
}
