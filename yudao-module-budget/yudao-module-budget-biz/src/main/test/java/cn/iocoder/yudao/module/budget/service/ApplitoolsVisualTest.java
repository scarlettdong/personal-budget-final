package cn.iocoder.yudao.module.budget.service;

import com.applitools.eyes.selenium.Eyes;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.applitools.eyes.RectangleSize;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class ApplitoolsVisualTest {

    private WebDriver driver;
    private Eyes eyes;

    @BeforeClass
    public void setUp() {
        // 初始化 WebDriver
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();

        // 初始化 Eyes
        eyes = new Eyes();
        eyes.setApiKey("YOUR_API_KEY");
        eyes.open(driver, "Your Application Name", "Test Name", new RectangleSize(800, 600));
    }

    @Test
    public void testVisualChanges() {
        // 导航到你的应用程序页面
        driver.get("https://your-app-url.com");

        // 捕捉整个窗口的视觉快照
        eyes.checkWindow("Page Name");
    }

    @AfterClass
    public void tearDown() {
        // 关闭 Eyes 和 WebDriver
        eyes.close();
        eyes.abortIfNotClosed();
        driver.quit();
    }
}
