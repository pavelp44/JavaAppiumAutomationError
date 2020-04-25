package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import junit.framework.TestCase;
import lib.ui.WelcomePageObject;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.time.Duration;

public class CoreTestCase extends TestCase {


    protected AppiumDriver driver;


    @Override
    protected void setUp() throws Exception {
        super.setUp();
        driver = Platform.getInstance().getDriver();
        this.rotateScreenPortrait();
        this.skipWelcomePageForIOSApp();
    }

    @Override
    public void tearDown() throws Exception {
        driver.quit();

        super.tearDown();
    }

    protected void rotateScreenPortrait() {
        driver.rotate(ScreenOrientation.PORTRAIT);
    }

    protected void rotateScreenLandscape() { driver.rotate(ScreenOrientation.LANDSCAPE); }

    protected void backgroundApp(int seconds) {
        driver.runAppInBackground(Duration.ofSeconds(seconds));
    }

    private void skipWelcomePageForIOSApp() throws Exception{
        if (Platform.getInstance().isIOS()){
            WelcomePageObject welcomePageObject = new WelcomePageObject(driver);
            welcomePageObject.clickSkip();
        }
    }

}