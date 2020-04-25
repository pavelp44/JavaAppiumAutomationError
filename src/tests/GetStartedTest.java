package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.WelcomePageObject;
import org.junit.Test;

public class GetStartedTest extends CoreTestCase {

    @Test

    public void testPassThroughWelcome(){

        if (Platform.getInstance().isAndroid()) return;

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
