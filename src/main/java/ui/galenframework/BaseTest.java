package ui.galenframework;

import static io.github.bonigarcia.wdm.DriverManagerType.CHROME;

import ui.galenframework.components.LoginPage;
import ui.galenframework.components.WelcomePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

public class BaseTest {

  protected WebDriver driver;
  protected WelcomePage welcomePage;
  protected LoginPage loginPage;

  @Before
  public void startUp() {
    String baseUrl = "http://testapp.galenframework.com/";
    WebDriverManager.getInstance(CHROME).setup();
    driver = new ChromeDriver();
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    driver.get(baseUrl);
    initElements();
  }

  private void initElements() {
    welcomePage = PageFactory.initElements(driver, WelcomePage.class);
    loginPage = PageFactory.initElements(driver, LoginPage.class);
  }

  @After
  public void tearDown() {
    if (driver != null) {
      driver.quit();
    }
  }

}
