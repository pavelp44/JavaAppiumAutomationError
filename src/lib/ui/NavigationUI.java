package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class NavigationUI extends MainPageObject{

    private static String
    MY_LISTS_LINK = "xpath://android.widget.FrameLayout[@content-desc='My lists']";

    public NavigationUI (AppiumDriver driver){
        super(driver);
    }

    public void clickToMyLists(){
        this.waitForElementAndClick(MY_LISTS_LINK,
                "Cannot find navigation button to my list",
                5);
    }

}
