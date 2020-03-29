package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class ArticleTests extends CoreTestCase {
    @Test

    public void testCompareArticleTitle() {

        SearchPageObject searchPageObject = new SearchPageObject(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");

        searchPageObject.clickByArticleWithSubString("Java (programming language)");

        ArticlePageObject articlePageObject = new ArticlePageObject(driver);

        String article_title = articlePageObject.getArticleTitle();

        assertEquals(
                "Wee see unexpected title",
                "Java (programming language)",
                article_title
        );
    }

    @Test


    public void testSwipeArticle() {


        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Appium");

        searchPageObject.clickByArticleWithSubString("Appium");

        ArticlePageObject articlePageObject = new ArticlePageObject(driver);

        articlePageObject.waitForTitleElement();

        articlePageObject.swipeToFooter();


    }

}
