package page.component;

import com.codeborne.selenide.SelenideElement;
import utils.RandomStringUtil;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CalendarComponent {

    private final SelenideElement
            yearDropdown = $("div.react-datepicker__year-dropdown-container > select"),
            monthDropdown = $("div.react-datepicker__month-dropdown-container > select");

    public LocalDate setRandomDate() {
        LocalDate randomDate = RandomStringUtil.getRandomBirthDate();
        setDate(randomDate);
        return randomDate;
    }

    public void setDate(LocalDate date) {
        yearDropdown.selectOption(String.valueOf(date.getYear()));

        String monthName = date.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        monthDropdown.selectOption(monthName);

        $$("div.react-datepicker__day:not(.react-datepicker__day--outside-month)")
                .findBy(text(String.valueOf(date.getDayOfMonth())))
                .click();
    }

    public static String formatDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM,yyyy", Locale.ENGLISH);
        return date.format(formatter);
    }
}