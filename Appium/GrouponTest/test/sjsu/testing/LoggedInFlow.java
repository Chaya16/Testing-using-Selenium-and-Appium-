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
import java.util.List;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LoggedInFlow {

    private static AndroidDriver ad;

    @BeforeClass
    public static void setUp() throws Exception {
        System.out.println("Starting Logged In Flow....");

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

        System.out.print("Logging Out....");

        navigateUp();
        navigateUp();
        navigateUp();
        navigateUp();

        waitForElementById(ad, "com.groupon:id/expand_account_box_indicator_group");
        ad.findElement(By.id("com.groupon:id/expand_account_box_indicator_group")).click();
        waitForElementByXPath(ad, "//android.widget.TextView[@text='Sign out']");
        ad.findElement(By.xpath("//android.widget.TextView[@text='Sign out']")).click();
        waitForElementById(ad, "com.groupon:id/dialog_positive_button");
        ad.findElement(By.id("com.groupon:id/dialog_positive_button")).click();

        System.out.print("Logged Out....");

        ad.quit();
        System.out.println("Finished Logged In Flow....");
    }

    @Test //Check Logged In State
    public void test1LoggedInState() {

        navigateUp();

        Assert.assertNotNull(ad.findElement(By.id("com.groupon:id/profile_name_text")));

        ad.findElement(By.xpath("//android.widget.ImageView[@content-desc='Your Cart']")).click();

    }

    @Test //Check if filter results are valid
    public void test2ValidFilterResult() {

        ad.findElement(By.id("com.groupon:id/tabs")).findElements(By.className("android.widget.TextView")).get(2).click();

        waitForElementById(ad, "com.groupon:id/title_bar");
        ad.findElement(By.id("com.groupon:id/title_bar")).click();

        waitForElementByXPath(ad, "//android.widget.TextView[contains(@text,'Electronics')]");
        ad.findElement(By.xpath("//android.widget.TextView[contains(@text,'Electronics')]")).click();

        waitForElementById(ad, "com.groupon:id/exposed_filters_scroll_view");
        WebElement filter = ad.findElement(By.id("com.groupon:id/exposed_filters_scroll_view")).findElements(By.className("android.widget.TextView")).get(1);
        filter.click();

        waitForElementById(ad, "com.groupon:id/single_filter_options_list");
        ad.findElement(By.id("com.groupon:id/single_filter_options_list")).findElements(By.className("android.widget.CheckBox")).get(1).click();

        waitForElementById(ad, "com.groupon:id/done_button");
        ad.findElement(By.id("com.groupon:id/done_button")).click();

        List<AndroidElement> items = ad.findElements(By.xpath("//android.widget.FrameLayout"));

        Assert.assertTrue(items.get(0).findElement(By.id("com.groupon:id/title")).getText().toLowerCase().contains(filter.getText().toLowerCase()));
    }

    @Test //Check if sorting result valid
    public void test3ValidSortResult() {

        waitForElementById(ad, "com.groupon:id/exposed_filters_scroll_view");
        WebElement filter = ad.findElement(By.id("com.groupon:id/exposed_filters_scroll_view")).findElements(By.className("android.widget.TextView")).get(3);
        filter.click();

        waitForElementById(ad, "com.groupon:id/single_filter_options_list");
        ad.findElement(By.id("com.groupon:id/single_filter_options_list")).findElements(By.className("android.widget.RadioButton")).get(2).click();

        waitForElementById(ad, "com.groupon:id/done_button");
        ad.findElement(By.id("com.groupon:id/done_button")).click();

        List<AndroidElement> items = ad.findElements(By.id("com.groupon:id/deal_card_view_container"));

        Float productOnePrice = Float.parseFloat(items.get(0).findElement(By.id("com.groupon:id/deal_card_new_price")).getText().substring(1).replace(",", ""));
        ad.swipe(470, 1400, 470, 1000, 400);
        Float productTwoPrice = Float.parseFloat(items.get(1).findElement(By.id("com.groupon:id/deal_card_new_price")).getText().substring(1).replace(",", ""));

        Assert.assertTrue(productOnePrice > productTwoPrice);
    }

    @Test //Check if product is linked to valid details page
    public void test4ValidProductDetailPage() {

        List<AndroidElement> items = ad.findElements(By.id("com.groupon:id/deal_card_view_container"));

        String productNameOnItemList = items.get(1).findElement(By.id("com.groupon:id/title")).getText();

        items.get(1).click();

        waitForElementById(ad, "com.groupon:id/title");
        String productNameOnDetailPage = ad.findElement(By.id("com.groupon:id/title")).getText();

        Assert.assertEquals(productNameOnItemList, productNameOnDetailPage);
    }

    @Test //Check if item count in Cart is valid
    public void test5ItemCountInCart() {

        waitForElementById(ad, "com.groupon:id/buy_button");
        ad.findElement(By.id("com.groupon:id/buy_button")).click();

        navigateUp();

        waitForElementById(ad, "com.groupon:id/buy_button");
        ad.findElement(By.id("com.groupon:id/buy_button")).click();

        waitForElementById(ad, "com.groupon:id/crystal_quantity_button_text");
        Assert.assertEquals("2", ad.findElement(By.id("com.groupon:id/crystal_quantity_button_text")).getText());
        ad.findElement(By.id("com.groupon:id/crystal_quantity_button_text")).click();

        waitForElementByXPath(ad, "//android.widget.TextView[contains(@resource-id,'crystal_quantity_button_text') and @text='+']");
        ad.findElement(By.xpath("//android.widget.TextView[contains(@resource-id,'crystal_quantity_button_text') and @text='+']")).click();
        Assert.assertEquals("3", ad.findElement(By.id("com.groupon:id/crystal_quantity_text")).getText());

        waitForElementById(ad, "com.groupon:id/submit");
        navigateUp();
        Assert.assertEquals("3", ad.findElement(By.id("com.groupon:id/shopping_cart_counter")).getText());

    }

    private static void navigateUp() {
        waitForElementByXPath(ad, "//android.widget.ImageButton[@content-desc='Navigate up']");
        ad.findElement(By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']")).click();
    }

    private static void waitForElementByXPath(AndroidDriver ad, String xPath) {
        WebDriverWait wait = new WebDriverWait(ad, 30);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xPath)));
    }

    private static void waitForElementById(AndroidDriver ad, String id) {
        WebDriverWait wait = new WebDriverWait(ad, 30);
        wait.until(ExpectedConditions.elementToBeClickable(By.id(id)));
    }
}