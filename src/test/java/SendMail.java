import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;


public class SendMail {

    tryToSendMail sendMail = new tryToSendMail();

    @BeforeClass
    public static void setup() {
        WebDriverContainer.getInstance().setDrivers();
    }

    @Test
    public void userLogin() throws InterruptedException {

        sendMail.getPage("https://mail.ru/");
        sendMail.enterMailBox("seleniumisgreat@mail.ru", "RGHSIISHU673HJf");
        sendMail.writeMessage("sky.techno@yandex.ru", "Test Message", "Test text Test text Test text Test text Test text");
        sendMail.checkSuccessStatus();
    }

    @AfterClass
    public static void tearDown() {
        WebDriverContainer.getInstance().closeDriver();
    }
}
