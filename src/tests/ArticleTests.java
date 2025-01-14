package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class ArticleTests extends CoreTestCase {
    @Test

    public void testCompareArticleTitle() {

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");

        searchPageObject.clickByArticleWithSubString("Java (programming language)");

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);

        String article_title = articlePageObject.getArticleTitle();

        assertEquals(
                "Wee see unexpected title",
                "Java (programming language)",
                article_title
        );
    }

    @Test


    public void testSwipeArticle() {


        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");

        searchPageObject.clickByArticleWithSubString("Java (programming language)");

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);

        articlePageObject.waitForTitleElement();

        articlePageObject.swipeToFooter();


    }

}
