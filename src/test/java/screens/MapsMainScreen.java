package screens;

import general.BasePage;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class MapsMainScreen extends BasePage {

    public MapsMainScreen(AndroidDriver driver) {
        super(driver);
        //PageFactory.initElements(driver, this);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    /*
     *
     *  Google maps UI elements found to validate and perform actions
     *
     *
     */
    @AndroidFindBy(id="com.google.android.apps.maps:id/search_omnibox_text_box")
    private WebElement searchOmniboxTxt;

    @AndroidFindBy(id="com.google.android.apps.maps:id/search_omnibox_edit_text")
    private WebElement enableSearchOmniboxTxt;

    @AndroidFindBy(id="com.google.android.apps.maps:id/watermark_image")
    private WebElement googWaterMarkImage;

    @AndroidFindBy(id="com.google.android.apps.maps:id/mylocation_button")
    private WebElement myLocationBtn;

    @AndroidFindBy(xpath="//android.view.View[@content-desc=\"Directions to Ciudad Colón\"]")
    private WebElement directionsBtn;

    @Override
    public boolean verifyLoads() {
        return waitForElementToBeVisible(searchOmniboxTxt)// && waitForElementToBeVisible(googWaterMarkImage)
               && waitForElementToBeVisible(myLocationBtn);
    }

    public boolean waitForAFewSecondsOnScreen() {
        return implicityWaitTimeOnScreen();
    }

    public boolean typeAnAddressInOmniBoxSearch(String location) {
        return tapElement(searchOmniboxTxt) &&
                sendTextOnCleanElement(enableSearchOmniboxTxt, location) &&
                pressKeyboardKey(AndroidKey.ENTER);
    }

    public boolean navigateToCC(){
        return tapElement(directionsBtn);
    }

    public boolean tapOnMyLocationBtn(){
        return tapElement(myLocationBtn);
    }
}
