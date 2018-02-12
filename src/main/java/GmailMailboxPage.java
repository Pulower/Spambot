import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class GmailMailboxPage extends WebElementManipulator {

    @FindBy(name = "to")
    WebElement mailAddress;

    @FindBy(name = "subjectbox")
    WebElement subjectBox;

    @FindBy(xpath = ".//*[@role=\"textbox\"]")
    WebElement messageField;

    @FindBy(xpath = ".//*[contains(text(),'Wyślij')]")
    WebElement sendBtn;

    public GmailMailboxPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public GmailMailboxPage chooseAddress(String address) {
        mailAddress.sendKeys(address);
        return this;
    }

    public GmailMailboxPage chooseSubjectAndMessage(String subject, String message) throws IOException {
        subjectBox.sendKeys(randomText(subject));
        messageField.sendKeys(randomText(message));
        return this;
    }

    public GmailMailboxPage sendMessage() {
        clickOnElement(sendBtn);
        return this;
    }

    public String randomText(String path) throws IOException {
        FileInputStream fs = new FileInputStream(path);
        BufferedReader br = new BufferedReader(new InputStreamReader(fs));
        ArrayList<String> array = new ArrayList<>();
        String line;
        while ((line = br.readLine()) != null)
            array.add(line);
        Random rand = new Random();
        int randomIndex = rand.nextInt(array.size());
        return array.get(randomIndex);
    }
}
