package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import org.junit.Test;


public class TestEx5 extends CoreTestCase {

    public void testSaveTwoArticlesToReadingList() {

        ArticlePageObject articlePageObject = new ArticlePageObject(driver);

        String name_of_folder = "My list";
        SearchPageObject searchPageObject = new SearchPageObject(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Dog");

        searchPageObject.clickByArticleWithSubString("Dog");


        articlePageObject.waitForTitleElement();

        articlePageObject.addArticleToMyList(name_of_folder);

        articlePageObject.close_article();

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Cat");

        searchPageObject.clickByArticleWithSubString("Cat");


        articlePageObject.waitForTitleElement();

        String article_title = articlePageObject.getArticleTitle();

        articlePageObject.addArticleToMyList(name_of_folder);

        articlePageObject.close_article();

        NavigationUI navigationUI = new NavigationUI(driver);

        navigationUI.clickToMyLists();

        MyListsPageObject myListsPageObject = new MyListsPageObject(driver);

        myListsPageObject.openFolderByName(name_of_folder);
        myListsPageObject.swipeByArticleToDelete(article_title);

    }

}



