import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GoogleMainPage extends WebElementManipulator {

    @FindBy(linkText = "Gmail")
    WebElement gmailLink;

    @FindBy(xpath = "/html/body/nav/div/a[2]")
    WebElement signInBtn;

    public GoogleMainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public GoogleMainPage gotoGmail() {
        clickOnElement(gmailLink);
        return this;
    }

    public GoogleMainPage clickSignIn() {
        clickOnElement(signInBtn);
        return this;
    }
}
