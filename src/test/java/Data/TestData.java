package Data;

import com.github.javafaker.Faker;
import java.util.Locale;

public class TestData {

    Faker faker = new Faker(new Locale("en-AU"));

    public String firstName = faker.name().firstName();
    public String lastName = faker.name().lastName();
    public String email = faker.internet().emailAddress();
    public String phoneNumber = faker.numerify("##########");;
    public String subject = faker.options().option("Maths", "Chemistry", "Economics");
    public String address = faker.address().fullAddress();
    public String selectedGender = faker.options().option("Male","Female","Other");
    public String selectedHobby = faker.options().option("Sports", "Reading", "Music");
    public String selectedState = faker.options().option("NCR","Uttar Pradesh","Haryana","Rajasthan");
    public String day = String.format("%02d", faker.number().numberBetween(1, 28));
    public String month = faker.options().option("January", "February", "March", "April",
            "May", "June", "July", "August", "September",
            "October", "November", "December");
    public String year = String.format("%s", faker.number().numberBetween(1901, 2015));

    public String selectedPicture = faker.options().option("mif10.jpg", "mif11.png", "mif12.bmp");;
    public String selectedCity = selectCity(selectedState);

    public String selectCity(String selectedState) {
        return switch (selectedState) {
            case "NCR" -> faker.options().option("Delhi", "Gurgaon", "Noida");

            case "Uttar Pradesh" -> faker.options().option("Agra", "Lucknow", "Merrut");

            case "Haryana" -> faker.options().option("Karnal", "Panipat");

            case "Rajasthan" -> faker.options().option("Jaipur", "Jaiselmer");
            default -> null;
        };
    }
}
