package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.By;

abstract public class MyListsPageObject extends MainPageObject{

public static String
    FOLDER_BY_NAME_TPL,
    ARTICLE_BY_TITLE_TPL;

    private static String getFolderXpathByName(String name_of_Folder){

        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", name_of_Folder);
    }

    private static String getSavedArticleXpathByTitle(String article_title){
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", article_title);
    }

    public MyListsPageObject (AppiumDriver driver){
            super(driver);
        }

    public void openFolderByName(String name_of_folder){

        String folder_name_xpath = getFolderXpathByName(name_of_folder);

        this.waitForElementAndClick(
                folder_name_xpath,
                "Cannot find reading list by provided name "+ name_of_folder,
                5);
    }

    public void waitForArticleToDisappearByTitle (String article_title){

        String article_xpath = getFolderXpathByName(article_title);
        this.waitForElementNotPresent(article_xpath, "Saved article still present with title" + article_title, 5);

    }

    public void waitForArticleToAppearByTitle (String article_title){

        String article_xpath = getFolderXpathByName(article_title);
        this.waitForElementPresent(article_xpath, "Cannot find saved article with title" + article_title, 5);

    }

    public void swipeByArticleToDelete (String article_title){


        if (Platform.getInstance().isIOS()){
            this.waitForArticleToAppearByTitle(article_title);
            String article_xpath = ARTICLE_BY_TITLE_TPL;
            this.swipeElementToLeft(article_xpath,
                    "Cannot swipe article in reading list");
            this.clickToTheRightUpperCorner(article_xpath, "Cannot find saved article");
        } else {
            this.waitForArticleToAppearByTitle(article_title);
            String article_xpath = getSavedArticleXpathByTitle(article_title);
            this.swipeElementToLeft(article_xpath,
                    "Cannot swipe article in reading list");


        }
        this.waitForArticleToDisappearByTitle(article_title);
    }
}
