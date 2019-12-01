
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
    public class FirstAppiumTest {
        public AppiumDriver driver;

        @Test
        public void test1(){
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setCapability("deviceName", "Pixel_2_API_24");
            desiredCapabilities.setCapability("platform", "Android");
            desiredCapabilities.setCapability("platformVersion", "7.0");
            desiredCapabilities.setCapability("app", "https://cybertek-resumes.s3.amazonaws.com/appium/Etsy+Handmade+Vintage+Goods_v5.30.0_apkpure.com.apk");
            desiredCapabilities.setCapability("adbExecTimeout", "20000");
            try {
                driver = new AndroidDriver<>(new URL("http://0.0.0.0:4723/wd/hub"), desiredCapabilities);
            } catch (Exception e) {
                e.printStackTrace();
            }
            driver.findElementByXPath("//*[@text='Search']").click();
            //we added explicit wait
            WebDriverWait wait = new WebDriverWait(driver, 10);
            //for mobile applications, accessibility id is like an in for web applications.
            //it's the best way to allocate element,
            //because it's the fastest and the most reliable way to find element
            //presenceOfElementLocated means that element should be present, not exactly visible
            //if element doesn't present, you will NoSuchElementException/element couldn't be found
            wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("Clothing Button")));
            driver.findElementByAccessibilityId("Clothing Button").click();
            wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.xpath("//*[@text='All']")));
            driver.findElement(By.xpath("//*[@text='All']")).click();
            //if you (isDisplayed(), and in case element doesn't present)
            //webdriver will fail on th findElement stage
            //but, if we use findElements
            //we are getting list of elements
            //if list is empty - element doesn't present
            //no failure will occur if list is empty
            if(driver.findElements(MobileBy.id("com.etsy.android:id/tooltip_background")).size()>0){
                //close banner
                driver.findElement(MobileBy.id("com.etsy.android:id/tooltip_x")).click();
            }
            wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.id("com.etsy.android:id/search_src_text")));
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(MobileBy.id("com.etsy.android:id/search_src_text")));

            //enter text
            driver.findElement(MobileBy.id("com.etsy.android:id/search_src_text")).sendKeys("wooden spoon");

            //click to search
            driver.findElement(MobileBy.id("com.etsy.android:id/search_mag_icon")).click();
        }
    }
