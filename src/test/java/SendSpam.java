import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class SendSpam {

    private WebDriver driver;
    private SendSpamPage spam;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "E:\\ChromeDriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("http://www.google.pl");
        driver.manage().window().maximize();
    }

    @Test
    public void signUp() {
        spam = new SendSpamPage(driver);
        spam.gotoGmail()
                .clickSignIn()
                .enterEmail()
                .clickNext()
                .enterPassword()
                .clickNext();
    }

    @Test(dependsOnMethods = "signUp")
    public void sendSpam() throws IOException {
        spam = new SendSpamPage(driver);
        spam.createNewMessage()
                .chooseAddress()
                .chooseSubjectAndMessage()
                .sendMessage();
        Assert.assertTrue(spam.getConfirm());

    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
