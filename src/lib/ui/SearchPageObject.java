package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.ui.factories.ArticlePageObjectFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;

abstract public class SearchPageObject extends MainPageObject{

    public static String
            SEARCH_INIT_ELEMENT,
            SEARCH_INPUT,
            SEARCH_CANCEL_BUTTON,
            SEARCH_RESULT_BY_SUBSTRING_TPL,
            SEARCH_RESULT_ELEMENT,
            SEARCH_EMPTY_RESULT_ELEMENT;

    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }


    private static String getSearchResultElement(String subString){
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{subString}", subString);
    }

    // Template Methods

    public void initSearchInput(){
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT, "Cannot find and click search init element", 15);
        this.waitForElementPresent(SEARCH_INIT_ELEMENT, "Cannot find search input after clicking search init element", 10);
    }

    public void waitForCancelButtonToAppear(){
        this.waitForElementPresent(SEARCH_CANCEL_BUTTON, "Cannot find Cancel Search button", 5);
    }

    public void waitForCancelButtonToDisAppear(){
        this.waitForElementNotPresent(SEARCH_CANCEL_BUTTON, "Cancel Search button is still present", 5);
    }

    public void clickCancelSearch(){
        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON, "Cannot find Cancel Search button and click", 5);
    }

    public void typeSearchLine(String search_line){
        this.waitForElementAndSendKeys(SEARCH_INPUT, search_line, "Cannot find and type in search input", 5);
    }

    public void waitForSearchResult(String subString){
        String search_result_xpath = getSearchResultElement(subString);
        this.waitForElementPresent(search_result_xpath, "Cannot find search result with substring " + subString);
    }

    public void clickByArticleWithSubString(String subString){
        String search_result_xpath = getSearchResultElement(subString);
        this.waitForElementAndClick(search_result_xpath, "Cannot find and click search result with substring " + search_result_xpath, 10);
    }

    public int getAmountOfFoundArticles(){

        this.waitForElementPresent(
                SEARCH_RESULT_ELEMENT,
                "Cannot find anything by the request",
                15);
        return this.getAmountOfElements(SEARCH_RESULT_ELEMENT);
    }

    public void waitForEmptyResultsLabel(){
        this.waitForElementPresent(SEARCH_EMPTY_RESULT_ELEMENT, "Cannot find empty result element", 15);
    }

    public void assertThereIsNoResultOfSearch(){
        this.assertElementNotPresent(SEARCH_RESULT_ELEMENT);

    }

    public void testChangeScreenOrientationOnSearchResults(){

        this.initSearchInput();
        this.typeSearchLine("Java");

        this.clickByArticleWithSubString("Java (programming language)");

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);

        String article_before_rotation = articlePageObject.getArticleTitle();

        driver.rotate(ScreenOrientation.LANDSCAPE);

    }
}
