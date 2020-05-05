package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfAllElementsLocatedBy;

abstract public class NavigationUI extends MainPageObject{

    public static String
    MY_LISTS_LINK,
    CLOSE_SYNC;


    public NavigationUI (AppiumDriver driver){
        super(driver);
    }

    public void clickToMyLists(){

        this.waitForElementAndClick(MY_LISTS_LINK,
                "Cannot find navigation button to my list",
                5);

        if (Platform.getInstance().isIOS()){
            this.waitForElementAndClick(CLOSE_SYNC,
                    "Cannot find button to close sync window",
                    5);
        }
        
    }

}
