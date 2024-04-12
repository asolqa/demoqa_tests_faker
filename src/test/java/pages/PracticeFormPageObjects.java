package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.components.CalendarComponent;
import pages.components.ModalFormComponent;


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

    @Step("Open registration page")
    public PracticeFormPageObjects openPage() {
        open("/automation-practice-form");

        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");

        formWrapper.shouldHave(text("Student Registration Form"));
        return this;
    }

    @Step("Enter first name {value}")
    public PracticeFormPageObjects setFirstName(String value) {
        firstNameInput.setValue(value);

        return this;
    }

    @Step("Enter last name {value}")
    public PracticeFormPageObjects setLastName(String value) {
        lastNameInput.setValue(value);

        return this;
    }
    @Step("Enter e-mail {value}")
    public PracticeFormPageObjects setEmail(String value) {
        userEmailInput.setValue(value);

        return this;
    }
    @Step("Set gender {value}")
    public PracticeFormPageObjects setGender(String value) {
        genderWrapper.$(byText(value)).click();

        return this;
    }
    @Step("Enter number {value}")
    public PracticeFormPageObjects setNumber(String value) {
        userNumberInput.setValue(value);

        return this;
    }
    @Step("Set date of birth {day}.{month}.{year}")
    public PracticeFormPageObjects setDateOfBirth(String day, String month, String year) {
        calendarInput.click();
        calendarComponent.setDate(day, month, year);

        return this;
    }

    @Step("Choose subject {value}")
    public PracticeFormPageObjects setSubject(String value) {
        subjectInput.setValue(value).pressEnter();

        return this;
    }

    @Step("Choose hobby {value}")
    public PracticeFormPageObjects setHobbies(String value) {
        hobbiesInput.$(byText(value)).click();

        return this;
    }
    @Step("Upload avatar {filename}")
    public PracticeFormPageObjects uploadFile(String filename) {
        fileInput.uploadFromClasspath(filename);

        return this;
    }
    @Step("Set address {value}")
    public PracticeFormPageObjects setAddress(String value) {
        addressInput.setValue(value);

        return this;
    }

    @Step("Set state {value}")
    public PracticeFormPageObjects setState(String value) {
        stateInput.click();
        stateCityInput.$(byText(value)).click();

        return this;
    }
    @Step("Set city {value}")
    public PracticeFormPageObjects setCity(String value) {
        cityInput.click();
        stateCityInput.$(byText(value)).click();

        return this;
    }
    @SuppressWarnings("UnusedReturnValue")
    @Step("Submit form")
    public PracticeFormPageObjects submitForm() {
        submitButton.click();

        return this;
    }
    @Step("Verify entered {value} appears for {key}")
    public PracticeFormPageObjects checkResult(String key, String value) {
        modalFormComponent.checkModalAppear(key, value);

        return this;
    }
    @SuppressWarnings("UnusedReturnValue")
    @Step("Verify no modal window appears")
    public PracticeFormPageObjects checkNoModal() {
        modalFormComponent.checkNoModalAppear();

        return this;
    }
}
