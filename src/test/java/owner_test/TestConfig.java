package owner_test;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.ConfigFactory;

@Config.Sources({
        "system:properties",
        "system:env",
        "classpath:${config.profile:local}.properties"  // по умолчанию 'local'
})
public interface TestConfig extends Config {

    @Key("browser")
    @DefaultValue("chrome")
    String browserName();

    @Key("version")
    @DefaultValue("")
    String browserVersion();

    @Key("size")
    @DefaultValue("1920x1080")
    String browserSize();

    @Key("url")
    @DefaultValue("https://demoqa.com")
    String url();

    @Key("remoteUrl")
    @DefaultValue("https://user1:1234@selenoid.autotests.cloud/wd/hub")
    String remoteUrl();

    @Key("is.remote")
    @DefaultValue("false")
    boolean isRemote();

    @Key("selenoid.video.enable")
    @DefaultValue("false")
    boolean videoEnable();

    @Key("selenoid.vnc.enable")
    @DefaultValue("false")
    boolean vncEnable();

    static TestConfig create() {
        // Проверяем, не установлен ли профиль явно
        if (System.getProperty("config.profile") == null) {
            // Создаем временный конфиг с дефолтными настройками
            TestConfig tempConfig = ConfigFactory.create(TestConfig.class);

            // Логика: если is.remote = true, используем remote профиль
            if (tempConfig.isRemote()) {
                System.setProperty("config.profile", "remote");
            } else {
                System.setProperty("config.profile", "local");
            }
        }

        // Создаем и возвращаем финальный конфиг
        return ConfigFactory.create(TestConfig.class);
    }

}
