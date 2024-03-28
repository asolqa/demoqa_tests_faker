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
        Student student = new Student();

        String firstname = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = faker.internet().emailAddress();
        String gender = faker.options().option("Male", "Female", "Other");
        //String gender = gender();
        String userPhone = faker.regexify("[0-9]{10}");
        LocalDate dateOfBirth = toLocalDate(faker.date().birthday());
        String dayOfBirth = String.format("%02d", dateOfBirth.getDayOfMonth());
        String monthOfBirth = dateOfBirth.getMonth().getDisplayName(TextStyle.FULL, locale);
        String yearOfBirth = String.format("%04d", dateOfBirth.getYear());
        String address = faker.address().streetAddress();
       // String subject = subject();
        String subject = faker.options().option("Maths", "Computer Science", "History", "English");
        String hobby = faker.options().option("Sports", "Reading", "Music");
       // String hobby = hobby();
       // String state = state();
       // String state = faker.options().option(STATES_TO_CITIES.keySet().toArray());
        String state = faker.options().option("NCR", "Uttar Pradesh", "Haryana", "Rajasthan");
        String city = city(state);

        student.setFirstname(firstname);
        student.setLastname(lastName);
        student.setEmail(email);
        //student.setEmail(String.format("%s.%s@example.com", firstname, lastName));
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

        return student;
    }

   /* private String gender() {
        int gender = faker.random().nextInt(0, 2);
        return switch (gender) {
            case 0 -> "Male";
            case 1 -> "Female";
            default -> "Other";
        };
    }

    private final static List<String> SUBJECTS = List.of("Maths", "Computer Science", "History", "English");

    private String subject() {
        int subjectId = faker.random().nextInt(0, SUBJECTS.size() - 1);
        return SUBJECTS.get(subjectId);
    }

    private final List<String> HOBBIES = List.of("Sports", "Reading", "Music");

    private String hobby() {
        int hobbyId = faker.random().nextInt(0, HOBBIES.size() - 1);
        return HOBBIES.get(hobbyId);
    }*/

    private static LocalDate toLocalDate(Date date) {
        return LocalDate.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    private static final Map<String, List<String>> STATES_TO_CITIES = Map
            .of("NCR", List.of("Delhi", "Gurgaon", "Noida"),
                    "Uttar Pradesh", List.of("Agra", "Lucknow", "Merrut"),
                    "Haryana", List.of("Karnal", "Panipat"),
                    "Rajasthan", List.of("Jaipur", "Jaiselmer")
            );

    /*  private String state() {
        int stateId = faker.random().nextInt(0, STATES_TO_CITIES.keySet().size() - 1);
        return STATES_TO_CITIES.keySet().stream().toList().get(stateId);
    }*/

    private String city(String state) {
        List<String> cities = STATES_TO_CITIES.get(state);
        int citiId = faker.random().nextInt(0, cities.size() - 1);
        return cities.get(citiId);
    }
}
