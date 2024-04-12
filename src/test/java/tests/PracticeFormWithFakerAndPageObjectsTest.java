package tests;

import io.qameta.allure.Step;
import org.junit.jupiter.api.*;
import pages.PracticeFormPageObjects;
import data.DataFactory;
import data.Student;

import java.util.Locale;


public class PracticeFormWithFakerAndPageObjectsTest extends TestBase {

    PracticeFormPageObjects practiceFormPage = new PracticeFormPageObjects();

    DataFactory dataFactory = new DataFactory(Locale.ENGLISH);

    Student student;

    @BeforeEach
    @Step("Prepare data")
    void prepareData() {

        student = dataFactory.newStudent();

    }

    @Test
    @Tag("regression")
    @DisplayName("Positive case: providing all data fo registration")
    void fillFullFormTest() {

        practiceFormPage.openPage()
                .setFirstName(student.getFirstname())
                .setLastName(student.getLastname())
                .setEmail(student.getEmail())
                .setGender(student.getGender())
                .setNumber(student.getUserPhone())
                .setDateOfBirth(student.getDayOfBirth(), student.getMonthOfBirth(), student.getYearOfBirth())
                .setSubject(student.getSubject())
                .setHobbies(student.getHobby())
                .uploadFile(student.getAvatar())
                .setAddress(student.getAddress())
                .setState(student.getState())
                .setCity(student.getCity())
                .submitForm();

        //Assertions
        practiceFormPage.checkResult("Student Name", student.getFirstname() + " " + student.getLastname())
                .checkResult("Student Email", student.getEmail())
                .checkResult("Gender", student.getGender())
                .checkResult("Mobile", student.getUserPhone())
                .checkResult("Date of Birth", student.getDateOfBirthPrettified())
                .checkResult("Subjects", student.getSubject())
                .checkResult("Hobbies", student.getHobby())
                .checkResult("Picture", student.getAvatar())
                .checkResult("Address", student.getAddress())
                .checkResult("State and City", student.getState() + " " + student.getCity());
    }

    @Test
    @Tag("regression")
    @DisplayName("Positive case: minimum data required")
    void fillMinDataSetTest() {

        //Name, gender, number
        practiceFormPage.openPage()
                .setFirstName(student.getFirstname())
                .setLastName(student.getLastname())
                .setGender(student.getGender())
                .setNumber(student.getUserPhone())
                .submitForm();

        //Assertions
        practiceFormPage.checkResult("Student Name", student.getFirstname() + " " + student.getLastname())
                .checkResult("Gender", student.getGender())
                .checkResult("Mobile", student.getUserPhone());
    }

    @Test
    @Tag("smoke")
    @DisplayName("Negative case: no user number provided")
    void negativeNoNumberTest() {

        //Name, gender but no number
        practiceFormPage.openPage()
                .setFirstName(student.getFirstname())
                .setLastName(student.getLastname())
                .setGender(student.getGender())
                .submitForm();

        //Assertions
        practiceFormPage.checkNoModal();
    }

    @Test
    @Tags({ @Tag("regression"),
            @Tag("smoke") })
    @DisplayName("Negative case: incorrect e-mail format ")
    void negativeWrongEmailTest() {
        String randomText = dataFactory.randomText(10);

        //Name, gender, number, wrong format email
        practiceFormPage.openPage()
                .setFirstName(student.getFirstname())
                .setLastName(student.getLastname())
                .setEmail(randomText)
                .setGender(student.getGender())
                .setNumber(student.getUserPhone())
                .submitForm();

        //Assertions
        practiceFormPage.checkNoModal();
    }
}
