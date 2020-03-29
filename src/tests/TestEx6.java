package tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MainPageObject;
import lib.ui.SearchPageObject;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class TestEx6 extends CoreTestCase {

    @Test

    public void testAssertTitle() {


        String search_line = "Linkin Park discography";
        ArticlePageObject articlePageObject = new ArticlePageObject(driver);

        SearchPageObject searchPageObject = new SearchPageObject(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(search_line);

        searchPageObject.clickByArticleWithSubString(search_line);
        MainPageObject mainPageObject = new MainPageObject(driver);
        mainPageObject.waitAllTestViewsToRender();
        assertTrue(articlePageObject.getArticleTitle().equals(search_line));
    }


}
