package cn.iocoder.yudao.module.budget.service;


import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.selenium.Eyes;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ApplitoolsVisualTest {

    private WebDriver driver;
    private Eyes eyes;

    @BeforeEach
    public void setUp() {

        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Google\\Chrome\\Application\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        // 初始化 WebDriver
        driver = new ChromeDriver(options);
        // 初始化 Eyes
        eyes = new Eyes();
        eyes.setApiKey("JThpdmr96FVI9HS0pFo1tWFwkvR2pqFaqApDuibdtys110");
        eyes.open(driver, "Budget Management System", "Test 1", new RectangleSize(800, 600));
    }

    @Test
    public void testVisualChanges() {
        // 导航到你的应用程序页面
        driver.get("http://localhost:80/login");

        // 捕捉整个窗口的视觉快照
        eyes.checkWindow("login");
    }

}
