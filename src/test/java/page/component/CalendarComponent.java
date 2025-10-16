package page.component;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import utils.RandomStringUtil;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class CalendarComponent {

    private final SelenideElement
            yearDropdown = $("div.react-datepicker__year-dropdown-container > select"),
            monthDropdown = $("div.react-datepicker__month-dropdown-container > select");

    private LocalDate selectedDate;

    public CalendarComponent setRandomDate() {
        this.selectedDate = RandomStringUtil.getRandomBirthDate();

        $(".react-datepicker").shouldBe(Condition.visible);

        setYear(selectedDate.getYear());
        setMonth(selectedDate.getMonthValue());
        setDay(selectedDate.getDayOfMonth());

        return this;
    }

    public void setDate(LocalDate date) {
        this.selectedDate = date;

        yearDropdown.selectOption(String.valueOf(date.getYear()));

        String monthName = date.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        monthDropdown.selectOption(monthName);

        String dayXpath = String.format(
                "//div[contains(@class, 'react-datepicker__day') and " +
                        "not(contains(@class, 'outside-month')) and " +
                        "text()='%d']",
                date.getDayOfMonth()
        );
        $(byXpath(dayXpath)).click();
    }

    private void setYear(int year) {
        yearDropdown.selectOption(String.valueOf(year));
    }

    private void setMonth(int month) {
        String monthName = Month.of(month).getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        monthDropdown.selectOption(monthName);
    }

    private void setDay(int day) {
        String dayXpath = String.format(
                "//div[contains(@class, 'react-datepicker__day') and " +
                        "not(contains(@class, 'outside-month')) and " +
                        "text()='%d']",
                day
        );
        $(byXpath(dayXpath)).shouldBe(Condition.visible).click();
    }

    public LocalDate getSelectedDate() {
        return selectedDate;
    }

    public String getFormattedSelectedDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM,yyyy", Locale.ENGLISH);
        return selectedDate.format(formatter);
    }
}