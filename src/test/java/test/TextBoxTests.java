package test;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TextBoxTests {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1200";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        Configuration.timeout = 10000;
    }

    @Test
    void fillFormTest() {
        open("/text-box");
        $("#userName").setValue("Tom");
        $("#userEmail").setValue("TomAdams@google.com");
        $("#currentAddress").setValue("Rio");
        $("#permanentAddress").setValue("Jakarta");
        $("#submit").click();

        $("#output").$("#name").shouldHave(text("Tom"));
        $("#output").$("#email").shouldHave(text("TomAdams@google.com"));
        $("#output").$("#currentAddress").shouldHave(text("Rio"));
        $("#output").$("#permanentAddress").shouldHave(text("Jakarta"));
    }
}
