package utils;

import com.github.javafaker.Faker;

import java.util.Locale;

public class RandomStringUtil {

    public static String getRandomFirstName() {
      Faker faker = new Faker(new Locale("en-AU"));
      return faker.name().firstName();
    }

    public static String getRandomLastName() {
        Faker faker = new Faker(new Locale("en-AU"));
        return faker.name().lastName();
    }

    public static String getRandomEmail() {
        Faker faker = new Faker(new Locale("en-AU"));
        return faker.internet().emailAddress();
    }

    public static String getRandomPhoneNumber() {
        Faker faker = new Faker();
        return faker.numerify("##########");
    }

    public static int getRandomIndex(int maxSize) {
        Faker faker = new Faker();
        return faker.number().numberBetween(0, maxSize);
    }

    public static String getRandomStringFromArray(String[] array) {
        Faker faker = new Faker();
        int randomIndex = faker.number().numberBetween(0, array.length);
        return array[randomIndex];
    }

    public static String getRandomSubjects() {
        String[] subjects = {"Maths", "Economics", "Chemistry", "History"};
        return getRandomStringFromArray(subjects);
    }

    public static String getRandomAddress() {
        Faker faker = new Faker(new Locale("en-AU"));
        return faker.address().secondaryAddress();
    }
}
