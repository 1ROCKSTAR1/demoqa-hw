package owner_test;

import org.aeonbits.owner.Config;

public interface TestConfig extends Config {

    @Key("browser")
    @DefaultValue("chrome")
    String browserName();

    @Key("version")
    @DefaultValue("121.0")
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

}
