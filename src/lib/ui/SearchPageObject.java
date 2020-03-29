package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;

public class SearchPageObject extends MainPageObject{

    public static final String
            SEARCH_INIT_ELEMENT = "//*[@text='Search Wikipedia']",
            SEARCH_INPUT = "//*[contains(@text,'Search…')]",
            SEARCH_CANCEL_BUTTON = "org.wikipedia:id/search_close_btn",
            SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[@class='android.widget.TextView'][@text='{subString}']",
            SEARCH_RESULT_ELEMENT = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']",
            SEARCH_EMPTY_RESULT_ELEMENT = "//*[@text='No results found']";

    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }

    // Template Methods

    private static String getSearchResultElement(String subString){
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{subString}", subString);
    }

    // Template Methods

    public void initSearchInput(){
        this.waitForElementAndClick(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find and click search init element", 15);
        this.waitForElementPresent(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find search input after clicking search init element", 10);
    }

    public void waitForCancelButtonToAppear(){
        this.waitForElementPresent(By.id(SEARCH_CANCEL_BUTTON), "Cannot find Cancel Search button", 5);
    }

    public void waitForCancelButtonToDisAppear(){
        this.waitForElementNotPresent(By.id(SEARCH_CANCEL_BUTTON), "Cancel Search button is still present", 5);
    }

    public void clickCancelSearch(){
        this.waitForElementAndClick(By.id(SEARCH_CANCEL_BUTTON), "Cannot find Cancel Search button and click", 5);
    }

    public void typeSearchLine(String search_line){
        this.waitForElementAndSendKeys(By.xpath(SEARCH_INPUT), search_line, "Cannot find and type in search input", 5);
    }

    public void waitForSearchResult(String subString){
        String search_result_xpath = getSearchResultElement(subString);
        this.waitForElementPresent(By.xpath(search_result_xpath), "Cannot find search result with substring " + subString);
    }

    public void clickByArticleWithSubString(String subString){
        String search_result_xpath = getSearchResultElement(subString);
        this.waitForElementAndClick(By.xpath(search_result_xpath), "Cannot find and click search result with substring " + search_result_xpath, 10);
    }

    public int getAmountOfFoundArticles(){

        this.waitForElementPresent(
                By.xpath(SEARCH_RESULT_ELEMENT),
                "Cannot find anything by the request",
                15);
        return this.getAmountOfElements(By.xpath(SEARCH_RESULT_ELEMENT));
    }

    public void waitForEmptyResultsLabel(){
        this.waitForElementPresent(By.xpath(SEARCH_EMPTY_RESULT_ELEMENT), "Cannot find empty result element", 15);
    }

    public void assertThereIsNoResultOfSearch(){
        this.assertElementNotPresent(By.xpath(SEARCH_RESULT_ELEMENT));

    }

    public void testChangeScreenOrientationOnSearchResults(){
        SearchPageObject searchPageObject = new SearchPageObject(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");

        searchPageObject.clickByArticleWithSubString("Java (programming language)");

        ArticlePageObject articlePageObject = new ArticlePageObject(driver);

        String article_before_rotation = articlePageObject.getArticleTitle();

        driver.rotate(ScreenOrientation.LANDSCAPE);

    }
}
