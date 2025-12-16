package owner_test;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:config.properties")
public interface TestConfig extends Config {

    @Key("browser")
    @DefaultValue("chrome")
    String browser();

    @Key("browserSize")
    @DefaultValue("1920x1080")
    String browserSize();

    @Key("baseUrl")
    @DefaultValue("https://demoqa.com")
    String baseUrl();
}
