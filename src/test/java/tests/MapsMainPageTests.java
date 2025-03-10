package tests;

import general.MobileDriverManager;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.MapsMainPage;

public class MapsMainPageTests extends MobileDriverManager {

    private MapsMainPage mapsMainPage;

    public MapsMainPageTests() {
    }

    @Test
    public void verifyMapsMainScreen() {
        mapsMainPage = new MapsMainPage(getDriver());
        Assert.assertTrue(mapsMainPage.verifyLoads(), "[ERROR]    Maps Screen does not loaded");
        Assert.assertTrue(mapsMainPage.typeAnAddressInOmniBoxSearch("San Jose, Curridabat"), "[ERROR]    The field was not filled");
        Assert.assertTrue(mapsMainPage.typeAnAddressInOmniBoxSearch("San Jose, Ciudad Colon"), "[ERROR]    The field was not filled");
        Assert.assertTrue(mapsMainPage.waitForAFewSecondsOnScreen(), "[ERROR]    The app did not halt on screen");
    }
}

// just a comment to commit