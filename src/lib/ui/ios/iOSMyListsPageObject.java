package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListsPageObject;

public class iOSMyListsPageObject extends MyListsPageObject {
    static {
        ARTICLE_BY_TITLE_TPL = "xpath://XCUIElementTypeLink[contains(@text='Java (programming language)')]";
    }
    public iOSMyListsPageObject(AppiumDriver driver){
        super(driver);
    }
}
