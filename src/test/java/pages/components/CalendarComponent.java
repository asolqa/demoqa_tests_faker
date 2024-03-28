package pages.components;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class CalendarComponent {

    public void setDate(String day, String month, String year) {
        $(".react-datepicker__year-select").$(byText(year)).click();
        $(".react-datepicker__month-select").$(byText(month)).click();

        String dayFormatted = String.format("%03d", Integer.valueOf(day));
        $(".react-datepicker__day--" + dayFormatted + ":not(.react-datepicker__day--outside-month)").click();
    }
}
