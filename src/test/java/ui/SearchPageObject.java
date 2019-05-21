package ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SearchPageObject extends MainPageObject{

    private static final String
        SEARCH_INIT_ELEMENT = "//*[contains(@text, 'Search Wikipedia')]",
        SEARCH_INPUT = "//*[contains(@text, 'Search Wikipedia')]",
        SEARCH_CANCEL_BUTTON = "org.wikipedia:id/search_close_btn",
        SEARCH_RESULT_SUBSTRING_TPL = "//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='{SUBSTRING}']",
        SEARCH_RESULT_ELEMENT = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']",
        SEARCH_EMPTY_RESULT_ELEMENT = "//*[@text='No results found']";
    public SearchPageObject(AppiumDriver driver)
    {
        super(driver);
    }

    /* TEMPLATES METHODS */
    private static String getResultSearchElement(String substring){
            return SEARCH_RESULT_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }
    /* END OF TEMPLATES METHODS */

    public void initSearchInput() {
        this.waitForElementAndClick(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find 'Search Wikipedia' input", 5
        );
        this.waitForElementPresent(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find search input after clicking search init element", 5
        );
    }
    public void typeSearchLine(String search_line) {
        this.waitForElementAndSendKeys(By.xpath( SEARCH_INIT_ELEMENT), search_line, "Cannot find searched input", 5
        );
    }
    public void waitForSearchResult(String substring){
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(By.xpath(search_result_xpath),
                "Cannot find Search result with substring " + substring,
                7
                );
    }

    public void waitForCancelButtonToAppear() {
        this.waitForElementAndClick(By.id(SEARCH_CANCEL_BUTTON), "Cannot find Search cancel button X to cancel search", 5
        );

    }
    public void clickCancelSearch() {
        this.waitForElementAndClick(By.id(SEARCH_CANCEL_BUTTON), "Cannot click Search cancel button X to cancel search", 5
        );

    }
    public void waitForCancelButtonToDisappear() {

        this.waitForElementNotPresent(By.id(SEARCH_CANCEL_BUTTON), "Search cancel button X is still present on the page", 5
        );
    }
    public void  clickByArticleWithSubString(String substring){
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(By.xpath(search_result_xpath),
                "Cannot find and click Search result with substring " + substring,
                7
        );
    }

    public int getAmountOfArticles() {
        String search_result_locator = SEARCH_RESULT_ELEMENT;

        this.waitForElementPresent(
                By.xpath(search_result_locator),
                "Cannot find anything by the request ",
                15
        );
        return this.getAmountOfElements(By.xpath(SEARCH_RESULT_ELEMENT));

    }
    public void waitForEmptyResultsLabel(){

        this.waitForElementPresent(
                By.xpath(SEARCH_EMPTY_RESULT_ELEMENT),
                "Cannot find empty result element",
                15
        );

    }
    public void assertThereIsNoResultOfSearch(){
        this.assertElementNotPresent(
                By.xpath(SEARCH_RESULT_ELEMENT),
                "We supposed not to find any results"
        );
    }


}
