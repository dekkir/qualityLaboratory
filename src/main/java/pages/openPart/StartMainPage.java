package pages.openPart;

import com.codeborne.selenide.SelenideElement;
import config.TestConfigFactory;
import pages.AbstractPage;
import pages.closePart.InboxPage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static java.lang.String.format;

/**
 * Реализует стартовую страницу (открытую часть)
 */
public class StartMainPage extends AbstractPage {

    private final SelenideElement leftColumn = $("[data-testid='grid-left-col']");
    private final SelenideElement login = $("[name='login']");
    private final SelenideElement password = $("[data-testid='password-input']");
    private final SelenideElement enterPasswordButton = $("[data-testid='enter-password']");
    private final SelenideElement enterButton = $("[data-testid='login-to-mail']");


    public StartMainPage() {
        open(TestConfigFactory.getInstance().getConfiguration().getUrl());
    }


    protected SelenideElement getLoadCriteria() {
        return leftColumn;
    }

    public InboxPage login(String login, String password) {
        waitUntilPageIsLoaded();
        InboxPage inboxPage = setLogin(login)
                .clickOnEnterPasswordButton()
                .setPassword(password)
                .clickOnEnterButton();

        inboxPage.waitUntilPageIsLoaded();
        return inboxPage;
    }

    private InboxPage clickOnEnterButton() {
        enterButton.click();
        logger.info("Произведено нажатие на кнопку [Войти]");
        return new InboxPage();
    }

    private StartMainPage setLogin(String login){
        this.login.setValue(login);
        logger.info(format("В поле [Login] установлено значение [%s]", login));
        return this;
    }

    private StartMainPage clickOnEnterPasswordButton(){
        enterPasswordButton.click();
        logger.info("Произведено нажатие на кнопку [Ввести пароль]");
        return this;
    }

    private StartMainPage setPassword(String password){
        this.password.setValue(password);
        logger.info(format("В поле [Password] установлено значение [%s]", password));
        return this;
    }
}
