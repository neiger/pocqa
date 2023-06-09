package general;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.net.URL;


public class MobileDriverManager extends TestListenerAdapter {

    private final static ThreadLocal<AndroidDriver> mobAppiumDriver = new ThreadLocal<>();

    private static int staticTime;
    private static int dynamicTime;

    public static int getStaticTime() {
        return staticTime;
    }
    public static int getDynamicTime() {
        return dynamicTime;
    }

    @Parameters({"tmStatic", "tmDynamic"})
    @BeforeMethod
    public void setMobDriverTimes(int tmStatic, int tmDynamic) {
        MobileDriverManager.staticTime = tmStatic;
        MobileDriverManager.dynamicTime = tmDynamic;
    }

    @Parameters({"platformName", "platformVersion", "deviceName", "automationName", "appPath","appActivity", "noReset", "appiumServer"})
    @BeforeMethod(alwaysRun = true)
    public final void setDriver(String platformName, String platformVersion, String deviceName,
                                String automationName, String appPath, String appActivity,
                                String noReset, String appiumServer) throws Exception {

        System.out.println("[DRIVER MSG]  ---- The mobile test driver is being initialized now");
try {
    DesiredCapabilities capability = new DesiredCapabilities();

    // Android Device capabilities
    capability.setCapability(MobileCapabilityType.PLATFORM_NAME, platformName);
    capability.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformVersion);
    capability.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
    capability.setCapability(MobileCapabilityType.AUTOMATION_NAME, automationName);
    capability.setCapability("appPackage", appPath);
    capability.setCapability("appActivity", appActivity);
    capability.setCapability(MobileCapabilityType.NO_RESET, noReset);

    mobAppiumDriver.set(new AndroidDriver(new URL(appiumServer), capability));
} catch (Exception e) {ErrorHandler.handle(e);}

    }

    // driver initiator which gets ready in the @BeforeMethod and does not require to be passed
    // as parameter in the ScreenTests classes
    public AndroidDriver getDriver() {
        return mobAppiumDriver.get();
    }

    @AfterMethod(alwaysRun = true)
    public void deleteDriver() throws NullPointerException{
        System.out.println("[DRIVER MSG]  ---- The browser driver is being close now");
        getDriver().quit();
    }

    @Override
    public void onTestFailure(ITestResult iTestResult){
        iTestResult.setStatus(2);
        System.out.println("THE TEST FAILED IS: " + iTestResult.getName());
    }
}