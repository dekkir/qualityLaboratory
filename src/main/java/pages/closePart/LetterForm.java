package pages.closePart;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import pages.AbstractPage;

import static com.codeborne.selenide.Selenide.*;
import static java.lang.String.format;

/**
 * Класс реализует форму написания письма
 */
public class LetterForm extends AbstractPage {

    private final SelenideElement signatureArea = $("[data-signature-widget='container']");

    private final SelenideElement recipientField = $x("//div[@data-name='to']//input");
    private final SelenideElement subjectField = $("[name='Subject']");
    private final SelenideElement bodyField = $("[role='textbox']");

    private final SelenideElement sendLetterButton =
            $x("//span[@class='button2__txt' and contains(., 'Отправить')]");

    private final SelenideElement letterSentSuccessfullyText =
            $x("//div/a[contains(@href, 'sent')]");

    @Override
    protected SelenideElement getLoadCriteria() {
        return signatureArea;
    }

    public LetterForm setRecipient(String email){
        recipientField.setValue(email);
        logger.info(format("В поле [Кому] установлено [%s]", email));
        return this;
    }

    public LetterForm setSubject(String subject){
        subjectField.setValue(subject);
        logger.info(format("В поле [Тема] установлено [%s]", subject));
        return this;
    }

    public LetterForm setLetterText(String letterText){
        bodyField.setValue(letterText);
        logger.info(format("В поле для письма установлен текст [%s]", letterText));
        return this;
    }

    public void sendLetter(){
        sendLetterButton.scrollIntoView(true).click();
        logger.info("Произведено нажатие на кнопку [Отправить]");
        letterSentSuccessfullyText.shouldBe(Condition.visible);
    }
}
