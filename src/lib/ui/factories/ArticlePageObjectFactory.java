package lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.android.AndroidArticlePageObject;
import lib.ui.android.AndroidSearchPageObject;
import lib.ui.ios.iOSArticlePageObject;
import lib.ui.ios.iOSSearchPageObject;

public class ArticlePageObjectFactory {

    public static ArticlePageObject get(AppiumDriver driver){
        if (Platform.getInstance().isIOS()){
            return new iOSArticlePageObject(driver);
        } else return new AndroidArticlePageObject(driver);
    }
}

