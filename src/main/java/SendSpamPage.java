import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class SendSpamPage extends WebElementManipulator {

    @FindBy(linkText = "Gmail")
    WebElement gmailLink;

    @FindBy(xpath = "/html/body/nav/div/a[2]")
    WebElement signInBtn;

    @FindBy(id = "identifierId")
    WebElement emailField;

    @FindBy(className = "CwaK9")
    WebElement nextBtn;

    @FindBy(name = "password")
    WebElement passwordField;

    @FindBy(xpath = "//*[@id=\":4g\"]/div/div")
    WebElement createMessage;

    @FindBy(name = "to")
    WebElement mailAddress;

    @FindBy(name = "subjectbox")
    WebElement subjectBox;

    @FindBy(id = ":a3")
    WebElement messageField;

    @FindBy(id = ":8r")
    WebElement sendBtn;

    @FindBy(id = "link_vsm")
    WebElement confirmMessage;

    public SendSpamPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public SendSpamPage gotoGmail() {
        clickOnElement(gmailLink);
        return this;
    }

    public SendSpamPage clickSignIn(){
        clickOnElement(signInBtn);
        return this;
    }

    public SendSpamPage enterEmail(){
        emailField.sendKeys("edwarddziaslo123@gmail.com");
        return this;
    }

    public SendSpamPage clickNext(){
        clickOnElement(nextBtn);
        return this;
    }

    public SendSpamPage enterPassword(){
        passwordField.sendKeys("edward123");
        return this;
    }

    public SendSpamPage createNewMessage(){
        clickOnElement(createMessage);
        return this;
    }

    public SendSpamPage chooseAddress(){
        mailAddress.sendKeys("spam666catcher@gmail.com");
        return this;
    }

    public SendSpamPage chooseSubjectAndMessage() throws IOException {
        subjectBox.sendKeys(randomText("subject.txt"));
        messageField.sendKeys(randomText("message.txt"));
        return this;
    }

    public SendSpamPage sendMessage(){
        clickOnElement(sendBtn);
        return this;
    }

    public boolean getConfirm(){
        return confirmMessage.isDisplayed();
    }

    public String randomText(String path) throws IOException {
        FileInputStream fs= new FileInputStream(path);
        BufferedReader br = new BufferedReader(new InputStreamReader(fs));
        ArrayList<String> array = new ArrayList<>();
        String line;
        while((line = br.readLine()) != null)
            array.add(line);
        Random rand = new Random();
        int randomIndex = rand.nextInt(array.size());
        return array.get(randomIndex);
    }
}
