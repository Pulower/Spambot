import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GmailSignInPage extends WebElementManipulator {

    @FindBy(id = "identifierId")
    WebElement emailField;

    @FindBy(className = "CwaK9")
    WebElement nextBtn;

    @FindBy(name = "password")
    WebElement passwordField;

    public GmailSignInPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public GmailSignInPage enterEmail(String email) {
        emailField.sendKeys(email);
        return this;
    }

    public GmailSignInPage clickNext() {
        clickOnElement(nextBtn);
        return this;
    }

    public GmailSignInPage enterPassword(String password) {
        passwordField.sendKeys(password);
        return this;
    }
}
