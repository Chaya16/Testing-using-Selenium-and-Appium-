package sjsu.testing;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LoggedOutFlow {

    private static AndroidDriver ad;
    private static String productNameOnList;

    @BeforeClass
    public static void setUp() throws Exception {
        System.out.println("Starting Logged Out Flow....");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "5.1");
        capabilities.setCapability("deviceName", "Nexus 5X API 22");
        capabilities.setCapability("appPackage", "com.groupon");
        capabilities.setCapability("appActivity", "com.groupon.activity.TodaysDeal");
        capabilities.setCapability("noReset", "true");

        ad = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);

    }

    @AfterClass
    public static void tearDown() throws Exception {
        navigateUp();
        navigateUp();
        navigateUp();
        navigateUp();
        //ad.quit();
        System.out.println("Finished Logged Out Flow....");
    }

    @Test
    public void test1LoggedOutState() {

        waitForElementByXPath(ad, "//android.widget.ImageButton[@content-desc='Navigate up']");
        ad.findElement(By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']")).click();

        Assert.assertNotNull(ad.findElement(By.id("com.groupon:id/login_text")));

        ad.findElement(By.xpath("//android.widget.ImageView[@content-desc='Your Cart']")).click();

    }

    @Test
    public void test2ValidSearchResult() {

        ad.findElement(By.id("com.groupon:id/search_icon")).click();

        waitForElementById(ad, "com.groupon:id/search_edittext");
        ad.findElement(By.id("com.groupon:id/search_edittext")).sendKeys("Restaurants");
        ad.findElement(By.id("com.groupon:id/search_icon")).click();

        waitForElementById(ad, "com.groupon:id/search_suggestions_recycle_list");
        ad.findElement(By.id("com.groupon:id/search_suggestions_recycle_list")).findElements(By.className("android.widget.RelativeLayout")).get(0).click();

        waitForElementById(ad, "com.groupon:id/exposed_filters_scroll_view");
        ad.findElement(By.id("com.groupon:id/exposed_filters_scroll_view")).findElements(By.className("android.widget.TextView")).get(0).click();

        waitForElementById(ad, "com.groupon:id/single_filter_options_list");
        String category = ad.findElement(By.id("com.groupon:id/single_filter_options_list")).findElements(By.className("android.widget.RadioButton")).get(1).getText();

        waitForElementById(ad, "com.groupon:id/done_button");
        ad.findElement(By.id("com.groupon:id/done_button")).click();

        Assert.assertTrue(category.toLowerCase().contains(ad.findElement(By.id("com.groupon:id/post_search_term")).getText().toLowerCase()));
    }

    @Test
    public void test3ValidFilterResult() {

        waitForElementById(ad, "com.groupon:id/exposed_filters_scroll_view");
        WebElement filter = ad.findElement(By.id("com.groupon:id/exposed_filters_scroll_view")).findElements(By.className("android.widget.TextView")).get(1);
        filter.click();

        waitForElementById(ad, "com.groupon:id/single_filter_options_list");
        ad.findElement(By.id("com.groupon:id/single_filter_options_list")).findElements(By.className("android.widget.RadioButton")).get(1).click();

        waitForElementById(ad, "com.groupon:id/done_button");
        ad.findElement(By.id("com.groupon:id/done_button")).click();

        List<AndroidElement> items = ad.findElements(By.id("com.groupon:id/deal_card_view_container"));

        String distanceMiles = items.get(0).findElement(By.id("com.groupon:id/deal_card_location")).getText().split("·")[1];
        Float distance = Float.parseFloat(distanceMiles.substring(0,distanceMiles.length()-3).trim());

        Assert.assertTrue(distance <= 5.0);

        productNameOnList = items.get(0).findElement(By.id("com.groupon:id/title")).getText();
    }

    @Test
    public void test4MapViewLoaded() {

        waitForElementById(ad, "com.groupon:id/action_bar_map_view");
        ad.findElement(By.id("com.groupon:id/action_bar_map_view")).click();

        waitForElementByXPath(ad, "//android.view.View[@content-desc='Google Map']");
        Assert.assertNotNull(ad.findElement(By.xpath("//android.view.View[@content-desc='Google Map']")));

        waitForElementByClass(ad, "android.view.View");
        ad.findElement(By.xpath("//android.view.View[@content-desc='Google Map']")).findElements(By.className("android.view.View")).get(0).click();

    }


    @Test
    public void test5CheckDetailPage() {

        waitForElementById(ad, "com.groupon:id/title");
        String productNameOnDetailPage = ad.findElement(By.id("com.groupon:id/title")).getText();

        Assert.assertTrue(isSimilar(productNameOnList, productNameOnDetailPage));

    }


    @Test
    public void test6CheckDealPurchase() {

        waitForElementById(ad, "com.groupon:id/buy_button");
        ad.findElement(By.id("com.groupon:id/buy_button")).click();

        waitForElementById(ad, "com.groupon:id/fragment_log_in_sign_up_google_button");
        ad.findElement(By.id("com.groupon:id/fragment_log_in_sign_up_google_button")).click();

        waitForElementById(ad, "com.google.android.gms:id/account_profile_picture");
        ad.findElement(By.id("com.google.android.gms:id/account_profile_picture")).click();

        waitForElementById(ad, "com.groupon:id/crystal_quantity_text");
        Assert.assertEquals("1", ad.findElement(By.id("com.groupon:id/crystal_quantity_text")).getText());

    }

    private static void waitForElementByXPath(AndroidDriver ad, String xPath) {
        WebDriverWait wait = new WebDriverWait(ad, 30);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xPath)));
    }

    private static void waitForElementById(AndroidDriver ad, String id) {
        WebDriverWait wait = new WebDriverWait(ad, 30);
        wait.until(ExpectedConditions.elementToBeClickable(By.id(id)));
    }

    private static void waitForElementByClass(AndroidDriver ad, String className) {
        WebDriverWait wait = new WebDriverWait(ad, 30);
        wait.until(ExpectedConditions.elementToBeClickable(By.className(className)));
    }

    private static void navigateUp() {
        waitForElementByXPath(ad, "//android.widget.ImageButton[@content-desc='Navigate up']");
        ad.findElement(By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']")).click();
    }

    private boolean isSimilar(String s1, String s2){
        Set<String> s1Set = new HashSet<String>();
        List<String> s2List = Arrays.asList(s2.split(" "));
        for(String s : s1.split(" "))
            s1Set.add(s);
        s1Set.retainAll(s2List);
        return s1Set.size() >= 3;
    }
}