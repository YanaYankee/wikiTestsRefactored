import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.time.Duration;

public class CoreTestCase extends TestCase {
    protected AppiumDriver driver;
    private static String AppiumURL = "http://127.0.0.1:4723/wd/hub";

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();

        DesiredCapabilities capabilities = new DesiredCapabilities();
        //    capabilities.setCapability("automationName", "uiautomator2");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "8");
        // capabilities.setCapability("automationName", "Appium"); Original error: Could not find a driver for automationName 'Appium' and platformName 'Android'. Please check your desired capabilities.
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "/Users/yaninapavlyk/Tests/wikiTestsRefactored/apks/org.wikipedia.apk");


        driver = new AndroidDriver(new URL(AppiumURL), capabilities);
// ****************  Set orientation before each test ***********************

        this.rotateScreenPortrait();

    }

    @Override
    protected void tearDown() throws Exception
    {
        driver.quit();
        super.tearDown();
    }
    protected void rotateScreenPortrait(){driver.rotate(ScreenOrientation.PORTRAIT);}
    protected void rotateScreenLandscape(){driver.rotate(ScreenOrientation.LANDSCAPE);}
    protected void backgroundApp(int seconds){driver.runAppInBackground(Duration.ofSeconds(seconds));}
}
