package config;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigBeanFactory;
import com.typesafe.config.ConfigFactory;

/**
 * Класс реализует загрузку различной конфигурации, необходимой для теста
 */
public class TestConfigFactory {
    private volatile Config config;
    private Configuration configuration;

    private TestConfigFactory() {
        if (config == null) {
            Config testConfig = ConfigFactory.systemProperties()
                    .withFallback(ConfigFactory.systemEnvironment())
                    .withFallback(ConfigFactory.parseResources("config.conf"));
            config = testConfig.withFallback(testConfig).resolve();
        }
    }

    public synchronized Configuration getConfiguration(){
        if(configuration == null){
            configuration = ConfigBeanFactory.create(config.getConfig("config"), Configuration.class);
        }
        return configuration;
    }

    public synchronized static TestConfigFactory getInstance(){
        return new TestConfigFactory();
    }
}
