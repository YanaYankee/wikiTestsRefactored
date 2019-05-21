import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import sun.plugin2.os.windows.SECURITY_ATTRIBUTES;
import ui.*;

import static org.openqa.selenium.By.id;

public class Tests extends CoreTestCase{
    private MainPageObject MainPageObject;

    protected void setUp() throws Exception {
        super.setUp();

        MainPageObject = new MainPageObject(driver);
    }

    @Test
    public void testChangeScreenOrientationOnSearchResults(){
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubString("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        String title_before_rotation = ArticlePageObject.getArticleTitle();

        this.rotateScreenLandscape();
        String title_after_rotation = ArticlePageObject.getArticleTitle();

        Assert.assertEquals(
                "Article title have been changed after rotation",
                title_before_rotation,
                title_after_rotation
        );
        this.rotateScreenPortrait();

        String title_after_second_rotation = ArticlePageObject.getArticleTitle();

        Assert.assertEquals(
                "Article title have been changed after rotation",
                title_before_rotation,
                title_after_second_rotation
        );

    }

@Test
public void testCheckSearchArticleInBackground() {
    SearchPageObject SearchPageObject = new SearchPageObject(driver);

    SearchPageObject.initSearchInput();
    SearchPageObject.typeSearchLine("Java");
    SearchPageObject.clickByArticleWithSubString("Object-oriented programming language");
    this.backgroundApp(2);
    SearchPageObject.clickByArticleWithSubString("Object-oriented programming language");
}

    //Ex 6 (Test: assert title)
    @Test
    public void testAssertElementPresent() {

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );
        MainPageObject.waitForElementAndSendKeys(
                By.xpath( "//*[contains(@text, 'Search Wikipedia')]"),
                "Java",
                "Cannot find searched input",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath(   "//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']" ),
                "Cannot find 'Object-oriented programming language' searching by 'Java'",
                7
        );

// **************** Assert that title is present ***********************

        int amount_of_search_results = MainPageObject.getAmountOfElements(
                By.id("org.wikipedia:id/view_page_title_text")
        );
        Assert.assertTrue("No title elements are found or there are more than 1 title on the article",
                amount_of_search_results == 1
        );

    }


//Ex 5 (Test: save 2 Articles to reading list)


    @Test
    //********************************* 1. Save 2 articles to one folder ***************************************
    public void testSaveTwoArticlesToMyList() {
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );
        MainPageObject.waitForElementAndSendKeys(
                By.xpath( "//*[contains(@text, 'Search Wikipedia')]"),
                "Java",
                "Cannot find searched input",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath(   "//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']" ),
                "Cannot find 'Object-oriented programming language' searching by 'Java'",
                7
        );
        MainPageObject.waitForElementPresent(
                id(  "org.wikipedia:id/view_page_title_text" ),
                "Cannot find article title",
                15
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot find button to open article options",
                7
        );

        MainPageObject.waitForElementPresent(
                By.xpath("//*[@text='Change language']"),
                "Cannot find button Add to reading list ",
                7
        );
        MainPageObject.waitForElementPresent(
                By.xpath("//*[@text='Share link']"),
                "Cannot find button Add to reading list ",
                7
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='Add to reading list']"),
                "Cannot find button Add to reading list ",
                7
        );
        MainPageObject.waitForElementAndClick(
                id( "org.wikipedia:id/onboarding_button" ),
                "Cannot find 'Got it' overlay ",
                5
        );
        MainPageObject.waitForElementAndClear(
                id( "org.wikipedia:id/text_input" ),
                "Cannot find input article folder",
                5
        );
//
//    waitForElementAndSendKeys(
//            By.id( "org.wikipedia:id/text_input" ),
//            "Learning programming",
//            "Cannot put text into articles folder input",
//            5
//    );

        String name_of_folder = "Learning programming";
        MainPageObject.waitForElementAndSetValue( id( "org.wikipedia:id/text_input" ),
                "Cannot send keys to name of folder input"+ name_of_folder,
                5,
                name_of_folder);

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'OK')]"),
                "Cannot press 'OK' btn",
                5
        );

        MainPageObject.waitForElementAndClick(
                id("org.wikipedia:id/menu_page_search"),
                "Cannot click Search btn",
                5
        );


        MainPageObject.waitForElementAndSendKeys(
                By.xpath( "//*[contains(@text, 'Search…')]"),
                "Appium",
                "Cannot find searched input",
                5
        );
        String name_second_article = "Appium";
        MainPageObject.waitForElementAndClick(
                By.xpath(   "//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='"+name_second_article+"']" ),
                "Cannot find 'Object-oriented programming language' searching by 'Java'",
                7
        );

        MainPageObject.waitForElementPresent(
                id(  "org.wikipedia:id/view_page_title_text" ),
                "Cannot find article title",
                15
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot find button to open article options",

                7
        );

        MainPageObject.waitForElementPresent(
                By.xpath("//*[@text='Change language']"),
                "Cannot find button Add to reading list ",
                7
        );
        MainPageObject.waitForElementPresent(
                By.xpath("//*[@text='Share link']"),
                "Cannot find button Add to reading list ",
                7
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='Add to reading list']"),
                "Cannot find button Add to reading list ",
                7
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='"+ name_of_folder + "']"),
                "Cannot find button Add to reading list ",
                7
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot close article, cannot find X btn",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                "Cannot close article, cannot find X btn",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='"+ name_of_folder + "']"),
                "Cannot close article, cannot find X btn",
                10
        );


//********************************* 2. Removes one of articles ***************************************


        MainPageObject.swipeElementToLeft(
                By.xpath("//*[@text='Java (programming language)']"),
                "Cannot find saved article"
        );
        MainPageObject.waitForElementNotPresent(
                By.xpath("//*[@text='Java (programming language)']"),
                "Delete saved article",
                5
        );
//********************************* 3. Assert that second is left unremoved ***************************************
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='"+name_second_article+"']"),
                "Cannot find saved article",
                5
        );
        //********************************* 4. Visit the article and assert that title text is expected ***************************************
        WebElement title_element = MainPageObject.waitForElementPresent(
                id(  "org.wikipedia:id/view_page_title_text" ),
                "Cannot find article title",
                15
        );
        String article_title = title_element.getAttribute("text");
        Assert.assertEquals(
                "We see unexpected title",
//                "Java (programming language)1", // to check if test fails
                name_second_article,
                article_title
        );




    }






    //Ex.4
    // Написать тест, который:
    //Ищет какое-то слово
    //Убеждается, что в каждом результате поиска есть это слово.

    @Test
    public void testSearchAndCheckSearchWordInResults() {

        // 1. Searches for "Selenium"
        MainPageObject.waitForElementAndClick(
                id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input",
                15
        );
        MainPageObject.waitForElementAndSendKeys(
                id("org.wikipedia:id/search_src_text"),
                "Selenium",
                //       "Seleniumdk;sv", //check the next step really works

                "Cannot find searched input",
                5
        );
        //2. Checks if that some articles are enlisted in the search result
        MainPageObject.waitForElementPresent(
                id("org.wikipedia:id/search_results_list"),
                "Search result is empty",
                5
        );
//3. Checks if the word is in each item of search result list
        MainPageObject.checkElementsToHaveWord(
                id("org.wikipedia:id/page_list_item_title"),
                "Selenium");
        //"Selenium"); //to check if test works

    }

//********************************************************************************************
    //Написать тест, который: Ищет какое-то слово. Убеждается, что найдено несколько стате.
    // Отменяет поиск. Убеждается, что результат поиска пропал

    @Test
    public void testSearchAndCheckResultsAndCancel(){

        // 1. Searches for "Selenium"
        MainPageObject.waitForElementAndClick(
                id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input",
                15
        );
        MainPageObject.waitForElementAndSendKeys(
                id("org.wikipedia:id/search_src_text"),
                "Selenium",
                //       "Seleniumdk;sv", //check the next step really works

                "Cannot find searched input",
                5
        );
        //2. Checks if that some articles are enlisted in the search result
        MainPageObject.waitForElementPresent(
                id("org.wikipedia:id/search_results_list"),
                "Search result is empty",
                5
        );
//3. Cancel search
        MainPageObject.waitForElementAndClick(
                id("org.wikipedia:id/search_close_btn"),
                "Cannot find X to cancel search",
                5
        );
//4. Check if search result articles list closed
        MainPageObject.waitForElementNotPresent(
                id("org.wikipedia:id/search_results_list"),
                "Search list was not closed",
                5
        );
    }


    @Test
    public void testClearAndCancelSearch() {
        MainPageObject.waitForElementAndClick(
                id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input",
                15
        );

//Написать функцию, которая проверяет наличие текста “Search…”
// в строке поиска перед вводом текста и помечает тест упавшим, если такого текста нет.
        WebElement search_text = MainPageObject.waitForElementAndClick(
                id("org.wikipedia:id/search_src_text"),
                "Cannot find 'Search Wikipedia' input",
                5
        );
        String search_input = search_text.getAttribute("text");
        Assert.assertEquals(
                "Cannot find text 'Search…' in Search input",
                "Search…",
                search_input

        );
//**********************************************************************
        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Java",
                "Cannot find searched input",
                5
        );


        MainPageObject.waitForElementAndClear(
                id("org.wikipedia:id/search_src_text"),
                "Cannot find search field",
                5
        );
//to check the test the following method is to be commented
        MainPageObject.waitForElementAndClick(
                id("org.wikipedia:id/search_close_btn"),
                "Cannot find X to cancel search",
                5
        );
        MainPageObject.waitForElementNotPresent(
                id("org.wikipedia:id/search_close_btn"),
                "X is still present on the page",
                5
        );
    }
    @Test
    public void testCancelSearch()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelButtonToDisappear();
    }

    @Test
    public void testSearch()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
    }

    @Test
    public void testCompareArticleTitle(){
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubString("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        String article_title = ArticlePageObject.getArticleTitle();

        Assert.assertEquals(
                "We see unexpected title",
//                "Java (programming language)1", // to check if test fails
                "Java (programming language)",
                article_title
        );

    }
    @Test
    public void testAmountOfNotEmptySearch() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        String search_line = "Linkin Park Diskography";
        SearchPageObject.typeSearchLine(search_line);
        int amount_of_search_results = SearchPageObject.getAmountOfArticles();

       Assert.assertTrue("We found too few results!",
                amount_of_search_results > 0
        );

    }

    @Test
    public void testAmountOfEmptySearch() {

        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        String search_line = ";kvz;dcdp";
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitForEmptyResultsLabel();
        SearchPageObject.assertThereIsNoResultOfSearch();



    }

    @Test
    public void testSaveFirstArticleToMyList() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubString("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement();

        String article_title = ArticlePageObject.getArticleTitle();
        String name_of_folder = "Learning programming";
        ArticlePageObject.addArticleToMyList(name_of_folder);
        ArticlePageObject.closeArticle();

        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickMyLists();

        MyListsPageObject MyListsPageObject = new MyListsPageObject(driver);
        MyListsPageObject.openFolderByName(name_of_folder);
        MyListsPageObject.swipeByArticleToDelete(article_title);
    }

    @Test
    public void testSwipeArticle(){
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Appium");
        SearchPageObject.clickByArticleWithSubString("Appium");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement();
        ArticlePageObject.swipeToFooter();
        }


}

