package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class Platform {
    private static final String
            PLATFORM_IOS = "ios",
            PLATFORM_ANDROID = "android",
            APPIUM_URL = "http://127.0.0.1:4723/wd/hub";

    public AppiumDriver getDriver() throws Exception {
        URL URL = new URL(APPIUM_URL);
        if (this.isAndroid()) {
            return new AndroidDriver(URL, this.getAndroidDesiredCapabilities());
        } else if (this.isIOS()) {
            return new IOSDriver(URL, this.getIOSDesiredCapabilities());
        } else {
            throw new Exception("Cannot detect type of the driver. Platform value:   " + this.getPlatformVar());
        }
    }

    public boolean isAndroid(){ return isPlatform(PLATFORM_ANDROID); }

    public boolean isIOS(){ return isPlatform(PLATFORM_IOS); }

    private DesiredCapabilities getAndroidDesiredCapabilities(){
        DesiredCapabilities capabilities = new DesiredCapabilities();
        //    capabilities.setCapability("automationName", "uiautomator2");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "8");
        // capabilities.setCapability("automationName", "Appium"); Original error: Could not find a driver for automationName 'Appium' and platformName 'Android'. Please check your desired capabilities.
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "/Users/yaninapavlyk/Tests/wikiTestsRefactored/apks/org.wikipedia.apk");
        return capabilities;
    }
    private DesiredCapabilities getIOSDesiredCapabilities(){
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "iOs");
        capabilities.setCapability("deviceName", "iPhone 8 Plus");
        capabilities.setCapability("platformVersion", "12.2");
        capabilities.setCapability("app", "/Users/yaninapavlyk/WIKI_NEW/Wikipedia.app");
        return capabilities;
    }
    private boolean isPlatform(String my_platform){
        String platform = this.getPlatformVar();
        return  my_platform.equals(platform);
    }

    private String getPlatformVar() {  return System.getenv("PLATFORM");  }

    private DesiredCapabilities getCapabilitiesByPlatformEnv() throws Exception {
        String platform = System.getenv("PLATFORM");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        if (platform.equals(PLATFORM_ANDROID)) {

        } else if (platform.equals(PLATFORM_IOS)){

        } else {
            throw new Exception("Cannot get run platform from env variable. Platform value " + platform);
        }
        return capabilities;
    }


}
