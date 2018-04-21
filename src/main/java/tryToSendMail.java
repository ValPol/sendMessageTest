import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

import java.util.concurrent.TimeUnit;

public class tryToSendMail {

    WebDriver driver;


    private final String login = "mailbox:login";

    private final String password = "mailbox:password";

    private final String submitBtn = "mailbox:submit";

    private final String writeMailBtn = "//div[@id='LEGO']//a[contains(@title, 'Написать письмо')]";

    private final String recipientField = "//textarea[@data-original-name='To']";

    private final String titleField = "//input[@name='Subject']";

    private final String sendBtn = "//div[@data-name=\"send\"]";

    private final String bodyTextSetScript = "tinyMCE.activeEditor.execCommand(\"mceInsertContent\", false, '<b>%s</b>');";

    private final String sendMessageSuccess = "//a[contains(text(), 'Перейти во Входящие')]";


    public tryToSendMail() {
        this.driver = WebDriverContainer.getInstance().getWebDriver();
    }

    public void getPage(String page) {
        driver.get(page);
    }

    public void enterMailBox(String loginCredentials, String passwordCredentials) {
        driver.findElement(By.id(login)).sendKeys(loginCredentials);
        driver.findElement(By.id(password)).sendKeys(passwordCredentials);
        driver.findElement(By.id(submitBtn)).click();
    }

    public void writeMessage(String recipient, String title, String text) throws InterruptedException {
        driver.findElement(By.xpath(writeMailBtn)).click();
        driver.findElement(By.xpath(recipientField)).sendKeys(recipient);
        driver.findElement(By.xpath(titleField)).sendKeys(title);
        setText(text);
        driver.findElement(By.xpath(sendBtn)).click();
    }

    public void checkSuccessStatus(){
        driver.findElement(By.xpath(sendMessageSuccess)).isDisplayed();
    }

    public void setText(String text) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            String testForMessageFullScript = String.format(bodyTextSetScript, text);
            js.executeScript(testForMessageFullScript);
        } catch (WebDriverException e) {
            System.out.println("Some problem with WebDriver");
            setText(text);
        }
    }


}
