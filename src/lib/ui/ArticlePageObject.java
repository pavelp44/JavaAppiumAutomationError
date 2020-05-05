package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.WebElement;

    public abstract class ArticlePageObject extends MainPageObject {

    protected static String
        TITLE,
        FOOTER_ELEMENT,
        OPTIONS_BUTTON,
        OPTIONS_ADD_TO_MY_LIST_BUTTON,
        ADD_TO_MY_LIST_OVERLAY,
        MY_LIST_NAME_INPUT,
        MY_LIST_OK_BUTTON,
        CLOSE_ARTICLE_BUTTON,
        EXISTING_LIST_TITLE;



        public ArticlePageObject(AppiumDriver driver){
        super(driver);
    }
    public WebElement waitForTitleElement(){
        return this.waitForElementPresent(
                TITLE,
                "Cannot find article title on page",
                10);
}

    public String getArticleTitle(){
        WebElement titleElement = waitForTitleElement();
        if (Platform.getInstance().isAndroid()) {
            return titleElement.getAttribute("text");
        }
        else return titleElement.getAttribute("name");
}
    public void swipeToFooter() {

        if (Platform.getInstance().isAndroid()) {
            this.swipeUpToFindElement(
                    FOOTER_ELEMENT,
                    "Cannot find the end of article",
                    40);

        } else {
            swipeUpTillElementAppear(FOOTER_ELEMENT, "Cannot find the end of article", 40);
        }
    }

    public void addArticleToMyList(String name_of_folder){
            MainPageObject mainPageObject = new MainPageObject(driver);

        mainPageObject.waitForElementAndClick(
                OPTIONS_BUTTON,
                "Cannot find three dots menu",
                5
        );

        mainPageObject.waitAllTestViewsToRender();

        mainPageObject.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Cannot find 'Add to reading list' ",
                5);

        mainPageObject.waitForElementAndClick(
                ADD_TO_MY_LIST_OVERLAY,
                "Cannot find 'Got it' tip overlay",
                5);

        mainPageObject.waitForElementAndClear(
                MY_LIST_NAME_INPUT,
                "Cannot find text input for reading list name",
                5);

        mainPageObject.setAndroidElementValue(
                MY_LIST_NAME_INPUT,
                name_of_folder,
                "Cannot find text input for reading list name"
        );

//        mainPageObject.waitForElementPresent(
//                By.id(MY_LIST_NAME_INPUT),
//                "Cannot find field 'reading list name'",
//                5);

        mainPageObject.waitForElementAndClick(
                MY_LIST_OK_BUTTON,
                "Cannot click OK to create reading list",
                5);

        mainPageObject.waitAllTestViewsToRender();

    }

        public void addArticleToExistingList(){
            MainPageObject mainPageObject = new MainPageObject(driver);

            mainPageObject.waitForElementAndClick(
                    OPTIONS_BUTTON,
                    "Cannot find three dots menu",
                    5
            );

            mainPageObject.waitAllTestViewsToRender();

            mainPageObject.waitForElementAndClick(
                    OPTIONS_ADD_TO_MY_LIST_BUTTON,
                    "Cannot find 'Add to reading list' ",
                    5);

            mainPageObject.waitForElementAndClick(EXISTING_LIST_TITLE, "Cannot find Existing reading list", 5);
        }

    public void close_article(){
        this.waitForElementAndClick(CLOSE_ARTICLE_BUTTON,
                "Cannot find and click close button",
                20);
    }

    public void addArticlesToMySaved(){
            this.waitForElementAndClick(OPTIONS_ADD_TO_MY_LIST_BUTTON,
                    "Cannot find option to add article to reading list",
                    5);
        }


}
