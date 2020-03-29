package tests;
import lib.CoreTestCase;
import lib.ui.SearchPageObject;


public class TestEx3 extends CoreTestCase{

    public void testEx3SearchCancel() throws Exception{

        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");

        assertTrue(
                "Number Of Articles less than 2",
                searchPageObject.getAmountOfFoundArticles() > 1);

        searchPageObject.clickCancelSearch();
        searchPageObject.assertThereIsNoResultOfSearch();

    }

}

