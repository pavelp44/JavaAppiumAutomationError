package lib.ui;

import io.appium.java_client.AppiumDriver;

abstract public class NavigationUI extends MainPageObject{

    public static String
    MY_LISTS_LINK;

    public NavigationUI (AppiumDriver driver){
        super(driver);
    }

    public void  clickToMyLists(){
        this.waitForElementAndClick(MY_LISTS_LINK,
                "Cannot find navigation button to my list",
                5);
    }

}
