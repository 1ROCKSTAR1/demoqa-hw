package test;

import data.Forms;
import data.Language;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selenide.*;

@Feature("DataDriven")
public class DataProviderTests extends BaseTest {

    @ValueSource(strings = {"tomAdams@google.com","tomAdams@yandex.ru","tomAdams@sharashka.org"})
    @ParameterizedTest
    @Tag("dataDrivenTests")
    @DisplayName("dataDriven автотест с ValueSource")
    public void severalEmailDomensTest(String email) {
        open("/text-box");
        executeJavaScript("$('footer').remove();");
        executeJavaScript("$('#fixedban').remove();");
        $("#userName").setValue("Tom Adams");
        $("#userEmail").setValue(email);
        $("#currentAddress").setValue("Moscow");
        $("#permanentAddress").setValue("Miami");
        $("#submit").click();
        $("#output").shouldHave(text(email));
    }

    @CsvFileSource(resources = "/data/data.csv")
    @ParameterizedTest(name = "для вкладки {0} должен отображаться текст {1}")
    @Tag("dataDrivenTests")
    @DisplayName("dataDriven автотест с CsvFileSource")
    public void tabsTest(String tabName, String text) {
        open("/tabs");
        executeJavaScript("$('footer').remove();");
        executeJavaScript("$('#fixedban').remove();");
        $("#demo-tab-"+tabName).click();
        Assertions.assertTrue($("#demo-tabpane-"+tabName).getText().contains(text));
    }

    static Stream<Arguments> demoQaData() {
        return Stream.of(
                Arguments.of(
                        Language.rus.getDisplayName(),
                        List.of("Lokasi", "Kapan", "Peserta")
                ),
                Arguments.of(
                        Language.ind.getDisplayName(),
                        List.of("Где", "Когда", "Кто")
                )
        );
    }

    @MethodSource("demoQaData")
    @ParameterizedTest
    @Tag("dataDrivenTests")
    @DisplayName("dataDriven автотест с MethodSource")
    public void languageTest(String language, List<String> expectedHeaders) {
        open("https://www.airbnb.com/");
        $("button span[data-button-content='true']").click();
        $("a:contains("+language+")").click();
        $(byTagAndText("button", "Понятно")).click();
        $$(".f1xmefpa").shouldHave(texts(expectedHeaders));

        $("[data-button-content='true']").parent().click();
        $("a:contains("+language+")").click();
        $$(".f1xmefpa").shouldHave(texts(expectedHeaders));
    }

    static Stream<Arguments> demoQaData2() {
        return Stream.of(
                Arguments.of(
                        Forms.Eng.getDisplayName(),
                        List.of("Press and news", "Events", "Partnerships", "Governance", "Quick Links")
                ),
                Arguments.of(
                        Forms.Esp.getDisplayName(),
                        List.of("Centro de medios", "Eventos", "Alianzas", "Gobernanza", "Enlaces rápidos")
                )
        );
    }

    @MethodSource("demoQaData2")
    @ParameterizedTest
    @Tag("dataDrivenTests")
    @DisplayName("dataDriven автотест с MethodSource")
    public void lestMenuTest(String form, List<String> expectedHeaders) {
        open("https://www.unesco.org/en");

        $("#block-languageswitcher").click();
        $("li[hreflang='"+form+"']").click();

        $$("$$('ul.navbar-nav a')").shouldHave(texts(expectedHeaders));

        $("#block-languageswitcher").click();
        $("li[hreflang='"+form+"']").click();

        $$("div.element-list.collapse.show li").shouldHave((texts(expectedHeaders)));
    }
}
