package general;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.AppiumFluentWait;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.*;

public abstract class BasePage {

    protected AndroidDriver driver;
    private final AppiumFluentWait<AndroidDriver> wait;

    private final int staticTimeOut;

    // CONSTRUCTOR - Receiving webdriver as a parameter to save it on a global variable to be used later
    public BasePage(AndroidDriver driver) {
        this.driver = driver;
        this.staticTimeOut = MobileDriverManager.getStaticTime();
        int dynamicTimeOut = MobileDriverManager.getDynamicTime();
        this.wait = new AppiumFluentWait<>(driver);
        this.wait.withTimeout(Duration.ofSeconds(dynamicTimeOut));

    }

    /****** GENERIC METHODS ******/

    public abstract boolean verifyLoads();

    // method to wait for the visibility of an element
    protected boolean waitForElementToBeVisible(WebElement element) {
        boolean flag;
        try {
            flag = this.wait.until(arg0 -> element != null && element.isDisplayed());
        } catch (NoSuchElementException e) { ErrorHandler.handle(e); flag = false; }

        return flag;
    }

    protected boolean waitForElementToBeClickable(WebElement element) {
        boolean flag;
        flag = this.wait.until(arg0 -> element.isEnabled());
        return flag;
    }

    // method to wait for an element to be clickable
    protected boolean tapElement(WebElement element) {
        boolean flag;
        flag = waitForElementToBeVisible(element) && waitForElementToBeClickable(element) &&
                this.wait.until(arg0 -> {
                    element.click();
                    return true;
                });
        return flag;
    }

    // method to enter text on specific field
    protected boolean sendTextOnCleanElement(WebElement element, String txt) {

        boolean validationReturn = false;

        if (waitForElementToBeClickable(element)) {
            element.click();
            element.clear();
            validationReturn = typeOnTxtElement(element, txt);
        }
        return validationReturn;
    }

    private boolean typeOnTxtElement(WebElement element, String txt) {
        element.sendKeys(txt);
        return element.isEnabled();
        //true;//element.getTagName().contains(txt);
    }

    // method to verify text on a certain element
    protected boolean verifyTextOnElement(WebElement element, String text) {
        boolean flag;
        flag = waitForElementToBeVisible(element) &&
                this.wait.until(arg0 -> element.getText().contains(text));
        return flag;
    }

    protected boolean implicityWaitTimeOnScreen() {
        try {
            TimeUnit.SECONDS.sleep(this.staticTimeOut);
            return true;
        } catch (Exception e) {
            ErrorHandler.handle(e);
            return false;
        }
    }

    protected String getTextFromElement(WebElement element) {
        String flag = "";
        if (waitForElementToBeVisible(element))
        {
            flag = element.getText();
        }
        return flag;
    }

    //     driver.getKeyboard().pressKey(Keys.ENTER);
    protected boolean pressKeyboardKey(AndroidKey keyValue) {
        boolean flag = false;
        if(keyValue != null) {
            driver.pressKey(new KeyEvent(keyValue));
            flag = true;
        } else {
            System.out.println("[ERROR]    There is a problem with the Key pressed");
        }

        return flag;
    }
}