package pages.components;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class ModalFinishWindowComponent {

    private final SelenideElement finishTable = $(".table-responsive");

    @Step("Провека поля {key} со значением {value}")
    public ModalFinishWindowComponent checkModalFinishWindow(String key, String value) {
        $(finishTable).$(byText(key)).parent().shouldHave(text(value));
        return this;
    }
}
