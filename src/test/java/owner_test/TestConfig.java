package owner_test;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:owner.properties",
        "classpath:${env}.properties"
})
public interface TestConfig extends Config {

    @Key("browser")
    @DefaultValue("chrome")
    String browserName();

    @Key("version")
    @DefaultValue("122.0")
    String browserVersion();

    @Key("size")
    @DefaultValue("1920x1080")
    String browserSize();

    @Key("remote.url")
    @DefaultValue("")
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
