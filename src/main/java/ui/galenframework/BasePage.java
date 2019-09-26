package ui.galenframework;

import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

  protected WebDriver driver;

  protected BasePage(WebDriver driver) {
    this.driver = driver;
  }

  protected boolean isElementDisplayed(WebElement element) {
    try {
      return element.isDisplayed();
    } catch (WebDriverException e) {
      return false;
    }
  }

  protected boolean isElementDisplayed(By by) {
    try {
      return driver.findElement(by).isDisplayed();
    } catch (WebDriverException e) {
      return false;
    }
  }

  protected void waitForClickable(WebElement element, long timeout) {
    new WebDriverWait(driver, timeout).until(ExpectedConditions.elementToBeClickable(element));
  }

  protected void waitForVisible(WebElement element, long timeout) {
    new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOf(element));
  }

  protected void waitForInvisible(WebElement element, long timeout) {
    new WebDriverWait(driver, timeout).until(ExpectedConditions.invisibilityOf(element));
  }

  protected void waitForConditionWithDelay(ExpectedCondition expectedCondition,
      int timeOutInSeconds, int sleepInSeconds) {
    new WebDriverWait(driver, timeOutInSeconds, sleepInSeconds * 1000)
        .until(expectedCondition);
  }

  protected void waitForElementTextToBe(WebElement element, String text, int timeOutSecs) {
    try {
      new WebDriverWait(driver, timeOutSecs)
          .until(ExpectedConditions.textToBePresentInElement(element, text));
    } catch (TimeoutException e) {
      //do nothing
    }
  }

  protected void waitForElementToBeChanged(WebElement element, int timeOutInSeconds) {
    try {
      new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.stalenessOf(element));
    } catch (TimeoutException e) {
      //do nothing
    }
  }

  protected void clickElementWithTimeout(WebElement element, int timeOutInSeconds,
      boolean isClickWithJs) {
    waitForClickable(element, timeOutInSeconds);
    if (isClickWithJs) {
      clickElementWithJs(driver, element);
    } else {
      element.click();
    }
  }

  protected void clickElementWithTimeout(WebElement element, int timeOutInSeconds) {
    clickElementWithTimeout(element, timeOutInSeconds, false);
  }

  protected void clickElementWithTimeout(WebElement element) {
    clickElementWithTimeout(element, 10, false);
  }

  protected void selectCheckbox(WebElement checkbox) {
    if (!checkbox.isSelected()) {
      checkbox.click();
    }
  }

  protected void unselectCheckbox(WebElement checkbox) {
    if (checkbox.isSelected()) {
      checkbox.click();
    }
  }

  protected void clearAndSendKeysToElement(WebElement element, String text) {
    element.clear();
    element.sendKeys(text);
  }

  protected void selectOptionByVisibleText(WebElement element, String textToSelect) {
    (new Select(element)).selectByVisibleText(textToSelect);
  }

  public boolean isOptionMatchingTextPresentInSelect(WebElement selectElement,
      String exactOptionText) {
    return new Select(selectElement)
        .getOptions()
        .stream()
        .map(WebElement::getText)
        .anyMatch(exactOptionText::equals);
  }

  public static void clickElementWithJs(WebDriver driver, WebElement element) {
    ((JavascriptExecutor) driver).executeScript(
        "arguments[0].click();",
        element);
  }

  public static void switchToLastOpenedBrowserTab(WebDriver driver) {
    Set<String> windowHandles = driver.getWindowHandles();
    String newWindow = windowHandles.toArray()[windowHandles.size() - 1].toString();
    driver.switchTo().window(newWindow);
  }
}

