package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class WelcomePageObject extends MainPageObject{

    private static final String
    STEP_LEARN_MORE_LINK = "xpath://*[@name='Learn more about Wikipedia']",
    NEXT_BUTTON_LINK = "xpath://XCUIElementTypeButton[@name='Next']",
    NEW_WAYS_TO_EXPLORE_LINK = "id:New ways to explore",
    ADD_OR_EDIT_PREFERRED_LANGUAGES_LINK = "xpath://XCUIElementTypeStaticText[@name='Add or edit preferred languages']",
    LEARN_MORE_ABOUT_DATA_COLLECTED_LINK = "/xpath:/XCUIElementTypeStaticText[@name='Learn more about data collected']",
    GET_STARTED_BUTTON_LINK = "xpath://XCUIElementTypeButton[@name='Get started']",
    SKIP = "xpath://XCUIElementTypeButton[@name='Skip']";

    public WelcomePageObject (AppiumDriver driver){
        super(driver);
    }
    public void waitForElementByMoreLink(){
        this.waitForElementPresent(STEP_LEARN_MORE_LINK, "Cannot find 'Learn more about Wikipedia' link'", 10);
    }
    public void clickToNextButton(){
    this.waitForElementAndClick(NEXT_BUTTON_LINK, "Cannot find and click 'Next' link", 10);
    }
    public void waitForNewWayToExploreText(){
        this.waitForElementPresent(NEW_WAYS_TO_EXPLORE_LINK, "Cannot find 'New ways to explore' link'", 10);
    }
    public void waitForAddOrEditPreferredLangText(){
    waitForElementPresent(ADD_OR_EDIT_PREFERRED_LANGUAGES_LINK, "Cannot find 'Add or edit preferred languages' text", 10);
    }

    public void waitForLearnMoreAboutDataCollectedText(){
        waitForElementPresent(LEARN_MORE_ABOUT_DATA_COLLECTED_LINK, "Cannot find 'Learn more about data collected' text", 10);
    }

    public void clickGetStartedButton(){
        this.waitForElementAndClick(GET_STARTED_BUTTON_LINK, "Cannot find and click 'Get starte' link", 10);
    }

    public void clickSkip() throws Exception{
        this.waitForElementAndClick(SKIP,"Cannot find and click Skip button", 5);
    }
}
