package com.galenframework.testapp.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WelcomePage {

  private WebDriver driver;

  @FindBy(css = "#welcome-page")
  private WebElement welcomeToOurTestPageHeader;

  @FindBy(css = ".button-login")
  private WebElement logInButton;

  public WelcomePage(WebDriver driver) {
    this.driver = driver;
  }

  public boolean isWelcomeHeaderDisplayed() {
    return welcomeToOurTestPageHeader.isDisplayed();
  }

  public void clickLoginButton() {
    logInButton.click();
  }
}
