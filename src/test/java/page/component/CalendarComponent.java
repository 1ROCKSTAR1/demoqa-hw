package page.component;

import static com.codeborne.selenide.Selenide.$;

public class CalendarComponent {

    public void setDate() {
        $(".react-datepicker__day--005").click();
    }
}
