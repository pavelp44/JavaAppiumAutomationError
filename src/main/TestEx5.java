package main;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class TestEx5 {

    private AppiumDriver driver;

    @Before

    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "8.0");
        capabilities.setCapability("AutomationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "/Users/pavel/Documents/JavaAppiumAutomation/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub/"), capabilities);
    }

    @After

    public void tearDown() {
        driver.quit();
    }

    @Test

    public void testSaveTwoArticlesToReadingList() {

        saveArticleToReadingList(
                "Cat",
                "My reading list",
                1
        );

        saveArticleToReadingList(
                "Dog",
                "My reading list",
                1
        );

        deleteOneArticle(
                "My reading list",
                "Cat"
        );
        if (isArticleNameCorrect("Dog") == true){
            waitForElementPresent(
                    By.id("org.wikipedia:id/view_page_title_text"),
                    "Cannot find 'Java (programming language)' title",
                    15
            );
        }

    }

    private void saveArticleToReadingList(String string_to_search, String name_of_reading_list, int position_of_article) {

        position_of_article--; //Done because index starts from 0

        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find search field",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                string_to_search,
                "Cannot find search input",
                5);

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container'][@index='" + position_of_article +"']"),
                "Cannot find " + position_of_article + " topic searching by "+ string_to_search,
                15
        );
        waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find title",
                15
        );

        waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot find three dots menu",
                5
        );

        waitForMenuToRender();


        waitForElementAndClick(
                By.xpath("//*[@text='Add to reading list']"),
                "Cannot find 'Add to reading list' option ",
                5);

        try {
            waitForElementPresent(
                    By.xpath("//android.widget.FrameLayout//*[@text='" + name_of_reading_list + "']"),
                    "Cannot find existing list. Creating new one",
                    5);
        }

        catch (Exception e) {
            waitForElementAndClick(
                    By.id("org.wikipedia:id/onboarding_button"),
                    "Cannot find 'Got it' tip overlay",
                    5);

            waitForElementAndClear(
                    By.id("org.wikipedia:id/text_input"),
                    "Cannot find text input for reading list name",
                    5);

            //      Method 'waitForElementAndSendKeys' isn't working here, replaced with following:
            AndroidElement androidElement = (AndroidElement) driver.findElement(By.id("org.wikipedia:id/text_input"));
            androidElement.click();
            androidElement.setValue(name_of_reading_list);

            waitForElementAndClick(
                    By.xpath("//*[@text='OK']"),
                    "Cannot click OK to create reading list",
                    5);
            waitForMenuToRender();

            waitForElementAndClick(By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                    "Cannot find and click close button",
                    7);

            return;
        }

            waitForElementAndClick(
                    By.xpath("//*[@text='"+ name_of_reading_list + "']"),
                    "Cannot find name of existing article",
                    5);

        waitForElementAndClick(By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot find and click close button",
                7);
    }

    private void deleteOneArticle(String name_of_list, String name_of_article) {

        waitForElementAndClick(By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                "Cannot find navigation button to my list",
                5);
        waitForMenuToRender();
        waitForElementAndClick(
                By.xpath("//*[@text='" + name_of_list + "']"),
                "Cannot find entry in reading list",
                5);

        swipeElementToLeft(By.xpath("//*[@text='" + name_of_article + "']"),
                "Cannot swipe 'Java (programming language)' article in reading list");

        waitForElementNotPresent(By.xpath("//*[@text='" + name_of_article + "']"),
                "Article still present",
                5);
    }

    private boolean isArticleNameCorrect(String name_of_article){
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='"+ name_of_article +"']"),
                "Cannot find topic " + name_of_article,
                15
        );
        return true;
    }


    private WebElement waitForElementPresent(By by, String error_meesage, long timeoutInSeconds){
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_meesage + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private WebElement waitForElementPresent(By by, String error_message){
        return waitForElementPresent(by, error_message, 5);
    }

    private WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds){

        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.click();
        return element;
    }
    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds){

        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    private boolean waitForElementNotPresent(By by, String error_message, long timeoutInSeconds){
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message+"\n");
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    private WebElement waitForElementAndClear(By by, String error_message, long timeoutInSeconds){
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }
    protected void swipeUp(int timeOfSwipe){
        Dimension size = driver.manage().window().getSize();

        int x = size.width/2;
        int start_y = (int) (size.height*0.8);
        int end_y = (int) (size.height*0.2);

        TouchAction action = new TouchAction(driver);
        action
                .press(x,start_y)
                .waitAction(timeOfSwipe)
                .moveTo(x,end_y)
                .release()
                .perform();

    }
    protected void swipeUpQuick(){
        swipeUp(200);
    }

    protected void swipeUpToFindElement(By by, String error_message, int max_swipes){
        int already_swiped = 0;
        while (driver.findElements(by).size() == 0){

            if (already_swiped > max_swipes){
                waitForElementPresent(by, "Cannot find element by swiping Up \n" + error_message, 0);
                return;
            }

            swipeUpQuick();
            already_swiped++;
        }
    }

    protected void swipeElementToLeft(By by, String error_message){
        WebElement element = waitForElementPresent(by, error_message);
        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;

        TouchAction action = new TouchAction(driver);
        action
                .press(right_x, middle_y)
                .waitAction(300)
                .moveTo(left_x, middle_y)
                .release()
                .perform();
    }


    protected void waitForMenuToRender(){
        WebDriverWait wait = new WebDriverWait(driver,3);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("android.widget.TextView")));
    }

    private int getAmountOfElements(By by){

        List elements = driver.findElements(by);
        return elements.size();

    }

    private void assertElementNotPresent(By by, String error_message){
        int amount_of_elements = getAmountOfElements(by);
        if (amount_of_elements > 0){
            String default_message = "An element '" + by.toString() + "' supposed to be not present";
            throw new AssertionError(default_message + " " + error_message);

        }
    }

    private String waitForElementAndGetAttribute(By by, String attribute, String error_message, long timeoutInSeconds){
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        return element.getAttribute(attribute);
    }

}



