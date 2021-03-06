import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GmailNavigationPage extends WebElementManipulator {

    @FindBy(xpath = ".//*[contains(text(),'UTWÓRZ')]")
    WebElement createMessage;

    @FindBy(id = "link_vsm")
    WebElement confirmMessage;

    public GmailNavigationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public GmailMailboxPage createNewMessage() {
        clickOnElement(createMessage);
        return new GmailMailboxPage(driver);
    }

    public boolean getConfirm() {
        return confirmMessage.isDisplayed();
    }
}
