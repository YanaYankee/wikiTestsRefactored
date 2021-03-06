package ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class WelcomePageObject extends MainPageObject
{
    private static final String
    STEP_LEARN_MORE_LINK = "Learn more about Wikipedia",
    STEP_NEW_WAYS_TO_EXPLORE_TEXT = "New ways to explore",
    STEP_ADD_OR_EDIT_PREFERRED_LANG = "Add or edit preferred languages",
    STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK = "Learn more about data collected",
    NEXT_LINK ="Next",
    GET_STARTED_BTN = "Get started";

    public WelcomePageObject(AppiumDriver driver)
    {
        super(driver);
    }
    public void waitForLearnMoreLink()
    {
        this.waitForElementPresent(By.id(STEP_LEARN_MORE_LINK), "Cannot find '" + STEP_LEARN_MORE_LINK + "' link", 10);
    }
    public void waitForNewWayToExploreText()
    {
        this.waitForElementPresent(By.id(STEP_NEW_WAYS_TO_EXPLORE_TEXT), "Cannot find '"+STEP_NEW_WAYS_TO_EXPLORE_TEXT+"' link", 10);
    }
    public void waitForAddOrEditPreferedText()
    {
        this.waitForElementPresent(By.id(STEP_ADD_OR_EDIT_PREFERRED_LANG), "Cannot find '"+STEP_ADD_OR_EDIT_PREFERRED_LANG+"' link", 10);
    }
    public void waitForLearnMoreAboutDataCollectedText()
    {
        this.waitForElementPresent(By.id(STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK), "Cannot find '"+STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK+"' link", 10);
    }
    public void clickNextButton()
    {
        this.waitForElementAndClick(By.id(NEXT_LINK), "Cannot find '"+NEXT_LINK+"' link", 10);
    }
    public void clickGetStartedButton()
    {
        this.waitForElementAndClick(By.id(GET_STARTED_BTN), "Cannot find '"+GET_STARTED_BTN+"' btn", 10);
    }

}
