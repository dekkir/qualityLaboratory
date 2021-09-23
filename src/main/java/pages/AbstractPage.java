package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;
import java.util.logging.Logger;

/**
 * Абстрактная страница, от которой наследуются реальные страницы приложения
 */
public abstract class AbstractPage {
    public Logger logger = Logger.getLogger(AbstractPage.class.getName());

    long DEFAULT_TIMEOUT_SEC = 20;

    public void waitUntilPageIsLoaded() {
        getLoadCriteria().shouldBe(Condition.visible, Duration.ofSeconds(DEFAULT_TIMEOUT_SEC));
    }

    protected abstract SelenideElement getLoadCriteria();
}
