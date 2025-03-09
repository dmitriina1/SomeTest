package Properties;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({"system:properties",
        "system:env",
        "file:src/main/resources/tests.properties"
})
public interface TestProperties extends Config{

    @Config.Key("Yandex.url")
    String YandexUrl();

    @Config.Key("default.timeout")
    int defaultTimeout();
}
