package cn.iocoder.yudao.module.budget.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class E2ETest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        // 设置 ChromeDriver 路径
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Google\\Chrome\\Application\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        // 初始化 WebDriver
        driver = new ChromeDriver(options);
    }

    @Test
    public void e2eTest() {
        // 导航到应用程序的URL
        driver.get("http://localhost:80/login");

        WebElement usernameInput = driver.findElement(By.id("username"));
        WebElement passwordInput = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("loginButton"));

        // 输入用户名和密码
        usernameInput.sendKeys("admin");
        passwordInput.sendKeys("admin123");

        // 点击登录按钮
        loginButton.click();

        // 验证登录后的页面标题
        String pageTitle = driver.getTitle();
        assertEquals("个人预算管理系统", pageTitle);

        // 关闭 WebDriver
        driver.quit();
    }

    @AfterEach
    public void tearDown() {
        // 关闭 WebDriver
        if (driver != null) {
            driver.quit();
        }
    }
}
