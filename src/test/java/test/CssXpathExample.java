package test;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class CssXpathExample {

    // <input type="email" class="inputtext login_form_input_box" name="email" id="email" data-testid="email">

    void cssXpath() {
        $("data-testid=email").setValue("1");
        $(by("data-testid", "email")).setValue("1");
        $x("//*[@data-testid='email']").setValue("1");

        // кейс, когда нет согласованного с разработчиками параметра для захвата локатора (без data-testid)
        // <input type="email" class="inputtext login_form_input_box" name="email" id="email">
        $("id=email").setValue("1");
        $(by("id", "email")).setValue("1");
        $(byId("email")).setValue("1"); //champion
        $("#email").setValue("1");
        $x("//*[@id='email']").setValue("1");

        // кейс, когда нет согласованного с разработчиками параметра для захвата локатора (без data-testid) и нет id
        // <input type="email" class="inputtext login_form_input_box" name="email">
        $("name=email").setValue("1");
        $(by("name", "email")).setValue("1");
        $(byName("email")).setValue("1"); //champion
        $x("//*[@name='email']").setValue("1");

        // кейс, когда нет согласованного с разработчиками параметра для захвата локатора (без data-testid),id и name
        // <input type="email" class="inputtext login_form_input_box">
        $("[class=inputtext][class=login_form_input_box").setValue("1");
        $(".inputtext.login_form_input_box").setValue("1");
        $(".login_form_input_box").setValue("1"); //champion
        $("input.inputtext.login_form_input_box").setValue("1");
        $x("//input[@class='inputtext'][@class='login_form_input_box']").setValue("1");

        // <div class="inputtext">
        //      <input type="email" class="login_form_input_box">
        // </div>
        $(".inputtext .login_form_input_box").setValue("1");
        $(".inputtext").$(".login_form_input_box").setValue("1");
        $("div.inputtext").$("input.login_form_input_box").setValue("1");
        $("div.inputtext input.login_form_input_box").setValue("1");

        // <div>Hello, Java!</div>
        $(byText("Hello, Java!"));
        $(withText("llo, Ja"));
        $x("//*[contains(text(),'Hello, Java')]");
    }
}