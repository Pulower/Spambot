import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class SendSpam {

    private WebDriver driver;
    private GmailMailboxPage mailbox;
    private GmailSignInPage gmail;
    private GoogleMainPage google;
    private GmailNavigationPage gmailNav;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "E:\\ChromeDriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://www.google.pl");
        driver.manage().window().maximize();
    }

    @Test(dataProvider = "SignInProvider")
    public void signIn(String email, String password) {
        google = new GoogleMainPage(driver);
        gmail = new GmailSignInPage(driver);
        google.gotoGmail()
                .clickSignIn()
                .enterEmail(email)
                .clickNext()
                .enterPassword(password)
                .clickNext();
    }

    @Test(dataProvider = "SendSpamProvider", dependsOnMethods = "signIn")
    public void sendSpam(String address, String subject, String message) throws IOException {
        mailbox = new GmailMailboxPage(driver);
        gmailNav = new GmailNavigationPage(driver);
        gmailNav.createNewMessage()
                .chooseAddress(address)
                .chooseSubjectAndMessage(subject, message)
                .sendMessage();
        Assert.assertTrue(gmailNav.getConfirm());
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }

    @DataProvider(name = "SignInProvider")
    public Object[][] getEmailAndPassword() {
        return new Object[][]{
                {"edwarddziaslo123@gmail.com", "edward123"}
        };
    }

    @DataProvider(name = "SendSpamProvider")
    public Object[][] getEmailParameters() {
        return new Object[][]{
                {"spam666catcher@gmail.com", "subject.txt", "message.txt"}
        };
    }
}
