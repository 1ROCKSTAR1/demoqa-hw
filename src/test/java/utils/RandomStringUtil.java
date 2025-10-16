package utils;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class RandomStringUtil {

    static Faker stringFaker = new Faker(new Locale("en-AU"));
    static Faker dataFaker = new Faker();

    public static String getRandomFirstName() {
        return stringFaker.name().firstName();
    }

    public static String getRandomLastName() {
        return stringFaker.name().lastName();
    }

    public static String getRandomEmail() {
        return stringFaker.internet().emailAddress();
    }

    public static String getRandomPhoneNumber() {
        return dataFaker.numerify("##########");
    }

    public static int getRandomIndex(int maxSize) {
        return dataFaker.number().numberBetween(0, maxSize);
    }

    public static String getRandomStringFromArray(String[] array) {
        int randomIndex = dataFaker.number().numberBetween(0, array.length);
        return array[randomIndex];
    }

    public static String getRandomSubjects() {
        String[] subjects = {"Maths", "Economics", "Chemistry", "History"};
        return getRandomStringFromArray(subjects);
    }

    public static String getRandomAddress() {
        return stringFaker.address().secondaryAddress();
    }

    private static final Map<String, List<String>> STATE_CITY_MAP = Map.of(
            "NCR", List.of("Delhi", "Gurgaon", "Noida"),
            "Uttar Pradesh", List.of("Agra", "Lucknow", "Merrut"),
            "Haryana", List.of("Karnal", "Panipat"),
            "Rajasthan", List.of("Jaipur", "Jaiselmer")
    );

    public static String getRandomState() {
        List<String> states = new ArrayList<>(STATE_CITY_MAP.keySet());
        return getRandomStringFromList(states);
    }

    public static String getRandomCity(String state) {
        List<String> cities = STATE_CITY_MAP.get(state);
        if (cities == null || cities.isEmpty()) {
            throw new IllegalArgumentException("No cities found for state: " + state);
        }
        return getRandomStringFromList(cities);
    }

    public static Map.Entry<String, String> getRandomStateAndCity() {
        String randomState = getRandomState();
        String randomCity = getRandomCity(randomState);
        return Map.entry(randomState, randomCity);
    }

    private static String getRandomStringFromList(List<String> list) {
        int randomIndex = stringFaker.number().numberBetween(0, list.size());
        return list.get(randomIndex);
    }

    public static LocalDate getRandomBirthDate() {
        int randomYear = dataFaker.number().numberBetween(1901, 2015);
        int randomMonth = dataFaker.number().numberBetween(1, 13);
        int randomDay = dataFaker.number().numberBetween(1, 29);

        return LocalDate.of(randomYear, randomMonth, randomDay);
    }

    public static String getRandomPicture() {
        String[] pictures = {"mif10.jpg", "mif11.png", "mif12.bmp"};
        return getRandomStringFromArray(pictures);
    }
}