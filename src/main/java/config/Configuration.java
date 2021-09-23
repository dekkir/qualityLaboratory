package config;

import lombok.Data;

/**
 * Класс реализует объект кнфигурации, содержающий необходимые данные для работы теста
 */
@Data
public class Configuration {
    private String emailWithoutDomain;
    private String password;

    private String url;

    private String recipient;

}