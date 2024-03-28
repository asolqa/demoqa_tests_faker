package pages;

import com.codeborne.selenide.SelenideElement;
import com.github.javafaker.Faker;
import pages.components.CalendarComponent;
import pages.components.ModalFormComponent;

import java.util.Locale;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
public class PracticeFormPageObjects {
    private final SelenideElement formWrapper =  $(".practice-form-wrapper"),

            firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            userEmailInput = $("#userEmail"),
            genderWrapper = $("#genterWrapper"),
            userNumberInput = $("#userNumber"),
            calendarInput = $("#dateOfBirthInput"),
            subjectInput = $("#subjectsInput"),
            hobbiesInput = $("#hobbiesWrapper"),
            addressInput = $("#currentAddress"),
            fileInput = $("#uploadPicture"),
            stateCityInput = $("#stateCity-wrapper"),
            stateInput = $("#state"),
            cityInput = $("#city"),
            submitButton = $("#submit");

    CalendarComponent calendarComponent = new CalendarComponent();
    ModalFormComponent modalFormComponent = new ModalFormComponent();

    public PracticeFormPageObjects openPage() {
        open("/automation-practice-form");
        formWrapper.shouldHave(text("Student Registration Form"));
        return this;
    }

    public PracticeFormPageObjects setFirstName(String value) {
        firstNameInput.setValue(value);

        return this;
    }

    public PracticeFormPageObjects setLastName(String value) {
        lastNameInput.setValue(value);

        return this;
    }
    public PracticeFormPageObjects setEmail(String value) {
        userEmailInput.setValue(value);

        return this;
    }
    public PracticeFormPageObjects setGender(String value) {
        genderWrapper.$(byText(value)).click();

        return this;
    }
    public PracticeFormPageObjects setNumber(String value) {
        userNumberInput.setValue(value);

        return this;
    }
    public PracticeFormPageObjects setDateOfBirth(String day, String month, String year) {
        calendarInput.click();
        calendarComponent.setDate(day, month, year);

        return this;
    }

    public PracticeFormPageObjects setSubject(String value) {
        subjectInput.setValue(value).pressEnter();

        return this;
    }

    public PracticeFormPageObjects setHobbies(String value) {
        hobbiesInput.$(byText(value)).click();

        return this;
    }
    public PracticeFormPageObjects uploadFile() {
        fileInput.uploadFromClasspath("avatar.png");

        return this;
    }
    public PracticeFormPageObjects setAddress(String value) {
        addressInput.setValue(value);

        return this;
    }

    public PracticeFormPageObjects setState(String value) {
        stateInput.click();
        stateCityInput.$(byText(value)).click();

        return this;
    }
    public PracticeFormPageObjects setCity(String value) {
        cityInput.click();
        stateCityInput.$(byText(value)).click();

        return this;
    }
    @SuppressWarnings("UnusedReturnValue")
    public PracticeFormPageObjects submitForm() {
        submitButton.click();

        return this;
    }
    public PracticeFormPageObjects checkResult(String key, String value) {
        modalFormComponent.checkModalAppear(key, value);

        return this;
    }
    @SuppressWarnings("UnusedReturnValue")
    public PracticeFormPageObjects checkNoModal() {
        modalFormComponent.checkNoModalAppear();

        return this;
    }
}
