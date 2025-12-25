package owner_test;

//import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.function.Supplier;

import static com.codeborne.selenide.Browsers.CHROME;
import static com.codeborne.selenide.Browsers.FIREFOX;

public class WebDriverProvider {//implements Supplier<WebDriver> {

//    private final TestConfig config;
//
//    public WebDriverProvider() {
//        this.config = ConfigFactory.create(TestConfig.class, System.getProperties());
//    }
//
//    @Override
//    public WebDriver get() {
//        WebDriver driver = createDriver();
//        driver.get(config.url());
//        return driver;
//    }
//
//    public WebDriver createDriver() {
//        switch (config.browserName()) {
//            case CHROME: {
//                WebDriverManager.chromedriver().setup();
//                return new ChromeDriver();
//            }
//            case FIREFOX: {
//                WebDriverManager.firefoxdriver().setup();
//                return new FirefoxDriver();
//            }
//            default: {
//                throw new RuntimeException("No such driver");
//            }
//        }
//    }
}
