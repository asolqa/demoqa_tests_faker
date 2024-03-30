package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.PracticeFormPageObjects;
import data.DataFactory;
import data.Student;

import java.util.Locale;

public class PracticeFormWithFakerAndPageObjectsTest extends TestBase {

    PracticeFormPageObjects practiceFormPage = new PracticeFormPageObjects();

    DataFactory dataFactory = new DataFactory(Locale.ENGLISH);

    Student student;

    @BeforeEach
    void prepareData() {
        student = dataFactory.newStudent();
    }

    @Test
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
