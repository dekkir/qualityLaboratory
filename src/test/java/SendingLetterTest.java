import com.sun.org.glassfish.gmbal.Description;
import config.Configuration;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.closePart.InboxPage;
import pages.closePart.LetterForm;
import pages.openPart.StartMainPage;

import java.util.Arrays;
import java.util.Date;

import static com.codeborne.selenide.Selenide.refresh;

public class SendingLetterTest extends BaseTest {

    String subject = "Лаборатория качества " + new Date().getTime();

    @Test(testName = "Отправка письма")
    @Description(value = "Тест выполняет авторизацию и отправляет письмо с заполнением body")
    public void sendLetterTest() {
        Configuration configuration = configFactory.getConfiguration();

        InboxPage inboxPage = new StartMainPage().login(configuration.getEmailWithoutDomain(), configuration.getPassword());
        LetterForm letterForm = inboxPage.clickOnWriteLetterButton();
        letterForm
                .setRecipient(configuration.getRecipient())
                .setSubject(subject)
                .setLetterText("Привет!")
                .sendLetter();

        refresh();

        checkThatLetterIsSent(inboxPage.openSentLetters().getListOfLetters());
    }

    private void checkThatLetterIsSent(String [] sentLetters){
           Assert.assertNotNull(
                   Arrays.stream(sentLetters)
                           .filter(x -> x.contains(subject))
                           .findFirst()
                           .orElse(null)
           );
    }
}
