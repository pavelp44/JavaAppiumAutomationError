package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class MyListsPageObject extends MainPageObject{

    public static final String
            FOLDER_BY_NAME_TPL = "//*[@class='android.widget.TextView'][@text='{FOLDER_NAME}']",
            FOLDER_BY_NAME_ON_OVERLAY_TPL = "//*[@text='{NAME_OF_READING_LIST}']",
            ARTICLE_BY_TITLE_TPL = "//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='{TITLE}']";

    public static String getFolderXpathByName(String name_of_Folder){

        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", name_of_Folder);
    }


    private static String getSavedArticleXpathByTitle(String article_title){

        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", article_title);
    }

    public static String getFolderXpathByNameOnOverlay(String name_of_folder){
        return FOLDER_BY_NAME_ON_OVERLAY_TPL.replace("{NAME_OF_READING_LIST}", name_of_folder);
    }


    public MyListsPageObject (AppiumDriver driver){
            super(driver);
        }

    public void openFolderByName(String name_of_folder){

        String folder_name_xpath = getFolderXpathByName(name_of_folder);

        this.waitForElementAndClick(
                By.xpath("//*[@text='"+ name_of_folder +"']"),
                "Cannot find reading list by provided name "+ name_of_folder,
                5);
    }

    public void waitForArticleToDisappearByTitle (String article_title){

        String article_xpath = getFolderXpathByName(article_title);
        this.waitForElementNotPresent(By.xpath(article_xpath), "Saved article still present with title" + article_title, 5);

    }

    public void waitForArticleToAppearByTitle (String article_title){

        String article_xpath = getFolderXpathByName(article_title);
        this.waitForElementPresent(By.xpath(article_xpath), "Cannot find saved article with title" + article_title, 5);

    }

    public void swipeByArticleToDelete (String article_title){

        this.waitForArticleToAppearByTitle(article_title);
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.swipeElementToLeft(By.xpath(article_xpath),
                "Cannot swipe article in reading list");

        this.waitForArticleToDisappearByTitle(article_title);
    }


}
