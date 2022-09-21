package com.telran.task.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class SearchItemTest extends TestBase {

    @Test
    public void searchItemTest() {
        selectMarket();
        switchToNextTab(1);
        selectExpressDepartment();
        acceptCookies();
        selectCategoryType("elektronika/23282330","smartfony-i-aksessuary/23282379");
        jumpDown();
        filterItem("Xiaomi","20000","35000");
        pause(10000);
        String itemName = getItemNameByOrder(2);
        typeInSearchInputField(itemName);
        pause(10000);
        String foundItemName = getItemNameByOrder(1);
        Assert.assertEquals(foundItemName,itemName);
    }

    public void acceptCookies() {
        click(By.cssSelector("[data-id='button-all']"));
    }

    public void typeInSearchInputField(String itemName) {
        type(By.id("header-search"),itemName);
        click(By.cssSelector("[data-r='search-button']"));
    }

    public String getItemNameByOrder(int number) {
        return driver.findElement(By.cssSelector("div:nth-child(" + number + ") ._2UHry")).getText();
    }

    public void filterItem(String priceFrom, String priceTo, String brand) {
        type(By.cssSelector("span._1heDd:nth-child(1) [class='_3qxDp']"), priceFrom);
        type(By.cssSelector("span._1heDd:nth-child(2) [class='_3qxDp']"),priceTo);
        pause(2000);
        click(By.xpath("//span[text()='" + brand + "']"));
    }

    public void jumpDown() {
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.PAGE_DOWN).perform();
    }

    public void selectCategoryType(String category, String category1) {
        click(By.cssSelector("[href='/catalog--" + category + "/list?filter-express-delivery=1&searchContext=express']"));
        pause(2000);
        click(By.cssSelector("[href='/catalog--" + category1 + "?how=dpop&glfilter=&cvredirect=3&filter-express-delivery=1&searchContext=express&track=srch_ddl']"));
    }

    public void switchToNextTab(int number) {
        List<String> availableTabs = new ArrayList<>(driver.getWindowHandles());
        if (!availableTabs.isEmpty()) {
            driver.switchTo().window(availableTabs.get(number));
        }
    }

    public void selectExpressDepartment() {
        click(By.xpath("//span[text()='Экспресс']"));
    }

    public void selectMarket() {
        click(By.cssSelector(".home-link2.services-pinned__item.services-pinned__all"));
        click(By.xpath("//*[text()='Маркет']"));
    }
}

