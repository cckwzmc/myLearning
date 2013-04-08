package com.toney.mvc.web.controller;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

/**
 * @ClassName: SeleniumHomeControllerTest
 * @Description: Selenium测试方法
 * @author toney.li
 * @date 2012-3-31 下午5:35:27
 * 
 */
@Ignore
public class SeleniumHomeControllerTest extends BaseControllerTestCase {
    WebDriver driver = null;

	@Before
	public void init() {
		driver = new HtmlUnitDriver();
//		driver=new FirefoxDriver();
//		driver=new ChromeDriver();
	}

    /**
     * @Title: testChekcedNotice
     * @Description: request method 必须一一对应 POST请求Post get 请求get
     * @param
     * @return void
     * @throws
     */
    public void testChekcedNotice() {
        // 完全装载页面后将控制返回给测试脚本
        // navigate().to()和get()功能完全一样。
        driver.get("http://localhost:8081/portal-web/home");

        // （XPATH返回第一个匹配到的元素，如果没有匹配到，抛出NoSuchElementException）
        // element = driver.findElement(By.xpath( "//input[@id=’xxx’]" ));
        WebElement email = driver.findElement(By.name("email"));
        WebElement phone = driver.findElement(By.name("phone"));

        // 任何页面元素都可以调用sendKeys，
        email.sendKeys("tom");
        phone.sendKeys("1234");

        // 提交表单
        driver.findElement(By.id("submit")).click();
        // driver.findElement(By.id( "submit" )).submit();
        // 要求element必须在表单中，否则抛出NoSuchElementException

        // 验证返回的内容
//        assertThat(driver.getPageSource(), containsString("登记成功"));
        WebElement body = driver.findElement(By.xpath("//body"));
//        assertThat(body.getText(), containsString("登记成功"));
//        assertThat(driver.getTitle(), equalTo("走秀portal2.5"));
    }

}
