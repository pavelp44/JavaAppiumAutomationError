package tests.iOS;

import lib.CoreTestCase;
import lib.ui.WelcomePageObject;
import org.junit.Test;

public class GetStartedTest extends CoreTestCase {

    @Test

    public void testPassThroughWelcome(){

        WelcomePageObject welcomePageObject = new WelcomePageObject(driver);

        welcomePageObject.waitForElementByMoreLink();
        welcomePageObject.clickToNextButton();

        welcomePageObject.waitForNewWayToExploreText();
        welcomePageObject.clickToNextButton();

        welcomePageObject.waitForAddOrEditPreferredLangText();
        welcomePageObject.clickToNextButton();

        welcomePageObject.waitForLearnMoreAboutDataCollectedText();
        welcomePageObject.clickGetStartedButton();

    }
}
