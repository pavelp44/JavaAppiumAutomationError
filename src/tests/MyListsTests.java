package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class MyListsTests extends CoreTestCase {

    private static String name_of_folder = "Learn programming";
    private static String article_title = "Cat";

    @Test

    public void testSaveFirstArticleToMyList() {

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");

        searchPageObject.clickByArticleWithSubString("Java (programming language)");

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);

        if (Platform.getInstance().isAndroid()) {
            articlePageObject.addArticleToMyList(name_of_folder);
            articlePageObject.waitForTitleElement();
            article_title = articlePageObject.getArticleTitle();
            articlePageObject.close_article();
        } else {
            articlePageObject.addArticlesToMySaved();
            articlePageObject.waitForTitleElement();
            articlePageObject.close_article();
            searchPageObject.clickCancelSearch();
        }

        NavigationUI navigationUI = NavigationUIFactory.get(driver);

        navigationUI.clickToMyLists();

        MyListsPageObject myListsPageObject = MyListPageObjectFactory.get(driver);

        if (Platform.getInstance().isAndroid()) {
            myListsPageObject.openFolderByName(name_of_folder);
            myListsPageObject.swipeByArticleToDelete(article_title);
        } else {
            myListsPageObject.swipeByArticleToDelete(article_title);
        }
    }


    public void testSaveTwoArticlesToReadingList() {

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Dog");

        searchPageObject.clickByArticleWithSubString("Dog");

        if (Platform.getInstance().isAndroid()) {
            articlePageObject.addArticleToMyList(name_of_folder);
            articlePageObject.waitForTitleElement();
            article_title = articlePageObject.getArticleTitle();
            articlePageObject.close_article();
        } else {
            articlePageObject.addArticlesToMySaved();
            articlePageObject.close_article();
            searchPageObject.clickCancelSearch();
        }

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Cat");

        searchPageObject.clickByArticleWithSubString("Cat");

        if (Platform.getInstance().isAndroid()) {
            articlePageObject.addArticleToExistingList();
            articlePageObject.waitForTitleElement();
            article_title = articlePageObject.getArticleTitle();
            articlePageObject.close_article();
        } else {
            articlePageObject.addArticlesToMySaved();
            articlePageObject.close_article();
            searchPageObject.clickCancelSearch();
        }

        NavigationUI navigationUI = NavigationUIFactory.get(driver);

        navigationUI.clickToMyLists();

        MyListsPageObject myListsPageObject = MyListPageObjectFactory.get(driver);
        if (Platform.getInstance().isAndroid()) {
            myListsPageObject.openFolderByName(name_of_folder);
            myListsPageObject.swipeByArticleToDelete(article_title);
        } else {
            myListsPageObject.swipeByArticleToDelete(article_title);
        }
        myListsPageObject.waitForArticleToAppearByTitle("Dog");
    }
}
