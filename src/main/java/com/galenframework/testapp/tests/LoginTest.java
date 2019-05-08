package com.galenframework.testapp.tests;

import com.galenframework.testapp.BaseTest;
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
}
