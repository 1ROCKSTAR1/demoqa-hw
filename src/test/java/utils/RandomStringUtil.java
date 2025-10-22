package utils;

import com.github.javafaker.Faker;
import java.util.Locale;

public class RandomStringUtil {

    static Faker faker = new Faker(new Locale("en-AU"));

    public static String getRandomFirstName() {
        return faker.name().firstName();
    }

    public static String getRandomLastName() {
        return faker.name().lastName();
    }

    public static String getRandomEmail() {
        return faker.internet().emailAddress();
    }

    public static String getRandomAddress() {
        return faker.address().secondaryAddress();
    }

    public String selectRandomCity(String selectedState) {
        return switch (selectedState) {
            case "NCR" -> faker.options().option("Delhi", "Gurgaon", "Noida");

            case "Uttar Pradesh" -> faker.options().option("Agra", "Lucknow", "Merrut");

            case "Harayana" -> faker.options().option("Karnal", "Panipat");

            case "Rajastan" -> faker.options().option("Jaipur", "Jaiselmer");
            default -> null;
        };
    }
}