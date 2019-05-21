package ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.openqa.selenium.By.id;

public class ArticlePageObject extends MainPageObject{
    private static final String
        TITLE = "org.wikipedia:id/view_page_title_text",
        FOOTER_ELEMENT = "//*[contains(@text, 'View page in browser')]",
        OPTIONS_BUTTON = "//android.widget.ImageView[@content-desc='More options']",
        OPTIONS_CHANGE_LANGUAGE_BTN = "//*[@text='Change language']",
        OPTIONS_SHARE_LINK_BTN = "//*[@text='Share link']",
        OPTIONS_ADD_TO_MY_LIST_BTN = "//*[@text='Add to reading list']",
        ADD_TO_MY_LIST_OVERLAY_BTN = "org.wikipedia:id/onboarding_button",
        MY_LIST_NAME_INPUT =  "org.wikipedia:id/text_input",
        MY_LIST_OK_BTN = "//*[contains(@text, 'OK')]",
        CLOSE_ARTICLE_BUTTON ="//android.widget.ImageButton[@content-desc='Navigate up']";

    public ArticlePageObject(AppiumDriver driver)
    {
        super(driver);
    }

    public WebElement waitForTitleElement() {
            return this.waitForElementPresent(By.id(TITLE), "Cannot find article title on page", 15);
        }
    public String getArticleTitle() {
        WebElement title_element = waitForTitleElement();
        return title_element.getAttribute("text");
    }
    public void swipeToFooter() {
        this.swipeUpToElement(
                By.xpath(FOOTER_ELEMENT),
                "Cannot find the end of article",
                20
        );
    }
    public void addArticleToMyList(String name_of_folder){
        this.waitForElementAndClick(
                By.xpath(OPTIONS_BUTTON),
                "Cannot find button to open article options",
                7
        );

        this.waitForElementPresent(
                By.xpath(OPTIONS_CHANGE_LANGUAGE_BTN),
                "Cannot find button Change language list ",
                7
        );
        this.waitForElementPresent(
                By.xpath(OPTIONS_SHARE_LINK_BTN),
                "Cannot find button Share link list ",
                7
        );
        this.waitForElementAndClick(
                By.xpath(OPTIONS_ADD_TO_MY_LIST_BTN),
                "Cannot find button Add to reading list ",
                7
        );
        this.waitForElementAndClick(
                By.id( ADD_TO_MY_LIST_OVERLAY_BTN ),
                "Cannot find 'Got it' overlay ",
                5
        );
        this.waitForElementAndClear(
                id( MY_LIST_NAME_INPUT ),
                "Cannot find input article folder",
                5
        );
        this.waitForElementAndSetValue(
                By.id(MY_LIST_NAME_INPUT),
                "Learning programming",
                5,
                name_of_folder);

        this.waitForElementAndClick(
                By.xpath(MY_LIST_OK_BTN),
                "Cannot press 'OK' btn",
                5
        );
    }
    public void closeArticle(){
        this.waitForElementAndClick(
                By.xpath(CLOSE_ARTICLE_BUTTON),
                "Cannot close article, cannot find X btn",
                5
        );
    }
}
