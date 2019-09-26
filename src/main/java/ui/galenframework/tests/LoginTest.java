package ui.galenframework.tests;

import ui.galenframework.BaseTest;
import org.junit.Assert;
import org.junit.Test;

public class LoginTest extends BaseTest {

  @Test
  public void loginButtonShouldBePresent() {
    Assert.assertTrue(welcomePage.isWelcomeHeaderDisplayed());
  }

  @Test
  public void clickingLoginButtonShouldRedirectYouToAnotherPage() {
    welcomePage.clickLoginButton();
    Assert.assertTrue(loginPage.isLoginHeaderDisplayed());
  }

  @Test
  public void asd() {
    welcomePage.clickLoginButton();
    Assert.assertTrue(loginPage.isLoginHeaderDisplayed());
  }
}
