package pages.closePart;

import com.codeborne.selenide.SelenideElement;
import pages.AbstractPage;

import static com.codeborne.selenide.Selenide.*;

/**
 * Класс реализует страницу работы с письмами (закрытая часть приложения)
 */
public class InboxPage extends AbstractPage {

    private final SelenideElement appCanvas = $("[id='app-canvas']");

    private final SelenideElement writeLetterButton = $("[title='Написать письмо']");

    private final SelenideElement sentLettersLink = $x("//a[contains(@title, 'Отправленные')]");

    private final static String LIST_OF_LETTERS_XPATH = "//div[@class='llc__item llc__item_title']";


    @Override
    protected SelenideElement getLoadCriteria() {
        return appCanvas;
    }

    public LetterForm clickOnWriteLetterButton() {
        waitUntilPageIsLoaded();
        writeLetterButton.click();
        logger.info("Произведено нажатие на кнопку [Напистаь письмо]");

        LetterForm letterForm = new LetterForm();
        letterForm.waitUntilPageIsLoaded();

        return letterForm;
    }

    public InboxPage openSentLetters() {
        sentLettersLink.click();
        logger.info("Произведено нажатие на ссылку [Отправленные]");
        waitUntilPageIsLoaded();
        return this;
    }

    public String[] getListOfLetters() {
        return $$x(LIST_OF_LETTERS_XPATH).stream().map(SelenideElement::getText).toArray(String[]::new);
    }
}
