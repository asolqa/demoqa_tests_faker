package data;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.TextStyle;
import java.util.*;

public class DataFactory {

    private final Locale locale;
    private final Faker faker;

    public DataFactory(Locale locale) {
        this.locale = locale;
        this.faker = new Faker(locale);
    }

    public Student newStudent() {

        String firstname = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = faker.internet().emailAddress(firstname + "." + lastName);
        String gender = faker.options().option("Male", "Female", "Other");
        String userPhone = faker.phoneNumber().subscriberNumber(10);
        LocalDate dateOfBirth = toLocalDate(faker.date().birthday());
        String dayOfBirth = String.format("%02d", dateOfBirth.getDayOfMonth());
        String monthOfBirth = dateOfBirth.getMonth().getDisplayName(TextStyle.FULL, locale);
        String yearOfBirth = String.format("%04d", dateOfBirth.getYear());
        String address = faker.address().streetAddress();
        String subject = faker.options().option("Maths", "Computer Science", "History", "English");
        String hobby = faker.options().option("Sports", "Reading", "Music");
        String state = faker.options().option("NCR", "Uttar Pradesh", "Haryana", "Rajasthan");
        String city = city(state);

        Student student = new Student();
        student.setFirstname(firstname);
        student.setLastname(lastName);
        student.setEmail(email);
        student.setGender(gender);
        student.setUserPhone(userPhone);
        student.setDayOfBirth(dayOfBirth);
        student.setMonthOfBirth(monthOfBirth);
        student.setYearOfBirth(yearOfBirth);
        student.setAddress(address);
        student.setSubject(subject);
        student.setHobby(hobby);
        student.setState(state);
        student.setCity(city);
        student.setAvatar("avatar.png");

        return student;
    }

    public String randomText(int size) {
        return faker.regexify(String.format("[a-z]{%d}", size));
    }

    private static LocalDate toLocalDate(Date date) {
        return LocalDate.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    private static final Map<String, List<String>> STATES_TO_CITIES = Map
            .of("NCR", List.of("Delhi", "Gurgaon", "Noida"),
                    "Uttar Pradesh", List.of("Agra", "Lucknow", "Merrut"),
                    "Haryana", List.of("Karnal", "Panipat"),
                    "Rajasthan", List.of("Jaipur", "Jaiselmer")
            );

    private String city(String state) {
        List<String> cities = STATES_TO_CITIES.get(state);
        int citiId = faker.random().nextInt(0, cities.size() - 1);
        return cities.get(citiId);
    }
}
