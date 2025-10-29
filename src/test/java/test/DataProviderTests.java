package test;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
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

import static com.codeborne.selenide.Condition.text;
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

    static Stream<Arguments> dataUnesco() {
        return Stream.of(
                Arguments.of(
                        Language.Eng.getDisplayName(),
                        List.of("About", "Expertise", "Impact", "Resources")
                ),
                Arguments.of(
                        Language.Esp.getDisplayName(),
                        List.of("Acerca de", "Competencia", "Impacto", "Recursos")
                )
        );
    }

    @MethodSource("dataUnesco")
    @ParameterizedTest
    @DisplayName("dataDriven автотест с MethodSource")
    public void unescoMenuTest(String lang, List<String> expectedHeaders) {

        open("https://www.unesco.org/" + lang);

        $$("ul[class='menu nav navbar-nav menu-level-0'] li")
                .filter(Condition.visible)
                .shouldHave(CollectionCondition.containExactTextsCaseSensitive(expectedHeaders));
        open("https://www.unesco.org/" + lang);

        $$("ul[class='menu nav navbar-nav menu-level-0'] li")
                .filter(Condition.visible)
                .shouldHave(CollectionCondition.containExactTextsCaseSensitive(expectedHeaders));
    }
}
