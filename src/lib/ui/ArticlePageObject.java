package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

    public class ArticlePageObject extends MainPageObject {

    private static final String
        TITLE = "id:org.wikipedia:id/view_page_title_text",
        FOOTER_ELEMENT = "xpath://*[@text='View page in browser']",
        OPTIONS_BUTTON = "xpath://android.widget.ImageView[@content-desc='More options']",
        OPTIONS_ADD_TO_MY_LIST_BUTTON = "xpath://*[@text='Add to reading list']",
        ADD_TO_MY_LIST_OVERLAY = "id:org.wikipedia:id/onboarding_button",
        MY_LIST_NAME_INPUT = "id:org.wikipedia:id/text_input",
        MY_LIST_OK_BUTTON = "xpath://*[@text='OK']",
        CLOSE_ARTICLE_BUTTON = "xpath://*[@resource-id='org.wikipedia:id/page_toolbar']//*[@class='android.widget.ImageButton']";


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
        return titleElement.getAttribute("text");
}
    public void swipeToFooter(){
            this.swipeUpToFindElement(
                    FOOTER_ELEMENT,
                    "Cannot find the end of article",
                    20);

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

    public void close_article(){
        this.waitForElementAndClick(CLOSE_ARTICLE_BUTTON,
                "Cannot find and click close button",
                20);
    }


}
