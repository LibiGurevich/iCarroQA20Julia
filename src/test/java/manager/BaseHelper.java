package manager;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class BaseHelper {

    Logger logger = LoggerFactory.getLogger(BaseHelper.class);


    WebDriver driver;

    public BaseHelper(WebDriver driver) {
        this.driver = driver;
    }

    private WebElement findElementBase(By locator) {
        System.out.println(locator);
        return driver.findElement(locator);
    }

    private List<WebElement> findElementsBase(By locator) {
        System.out.println(locator);
        return driver.findElements(locator);
    }

    public boolean isElementExist(By locator) {
        return findElementsBase(locator).size() > 0;
    }

    public String getTextBase(By locator){
        WebElement el = findElementBase((locator));
        return el.getText().trim().toUpperCase();
    }
    public void clickBase(By locator) {
        WebElement el = findElementBase(locator);
        el.click();
    }


    public void typeTextBase(By locator, String text) {
        WebElement el = findElementBase(locator);
        el.click();
        el.clear();
        el.sendKeys(text);
    }

    public boolean isTextEqual(By locator, String expectedResult) {
        String actualResult = getTextBase(locator);
        expectedResult = expectedResult.toUpperCase();

        if(expectedResult.equals(actualResult)) {
            return true;
        } else {
            System.out.println("expected result: " + expectedResult +
                    "actual result: " + actualResult);
            return false;
        }
    }

    public void jsClickBase(String locator) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(locator);
    }

    public boolean isTextContainsGet2Strings(String expectedResult, String actualResult) {
        if(actualResult.contains(expectedResult)) {
            return true;
        } else {
            System.out.println("expected result: " + expectedResult +
                    "actual result: " + actualResult);
            return false;
        }
    }

    public String getTextAlert() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        return alert.getText().toUpperCase().trim();
    }

    public void clickAcceptAlert() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        alert.accept();

//    public void clickByXY(By locator, double down, int right) {
//        Rectangle rect = findElementBase(locator).getRect();
//        int x = rect.getX() + (rect.getWidth() / right);
//        int y = (int) (rect.getY() + (rect.getHeight() / down));
//        Actions actions = new Actions(driver);
//        actions.moveByOffset(x, y).click().perform();

//        Rectangle rectangle = findElementBase(locator).getRect();
//        int x = rectangle.getX() + (rectangle.getWidth() / right);
//        int y =  (rectangle.getY() + (rectangle.getHeight() / down));
//
//        Actions actions = new Actions(driver);
//        actions.moveByOffset(x,y).click().perform();
    }

    public void refreshPage() {
        driver.navigate().refresh();;
    }

}
