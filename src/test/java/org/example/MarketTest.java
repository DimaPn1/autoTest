package org.example;

import helper.Computer;
import helper.Parser;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class MarketTest {

    public static MainPage mainPage;

    public static CompPage compPage;

    public static CatalogPage catalogPage;

    public static AllCompPage allCompPage;

    public static SpecificPage specificPage;

    public static WebDriver webDriver;

    @BeforeClass
    public static void setup(){

        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");

        webDriver = new ChromeDriver();

        mainPage = new MainPage(webDriver);
        catalogPage = new CatalogPage(webDriver);
        allCompPage = new AllCompPage(webDriver);
        compPage = new CompPage(webDriver);
        specificPage = new SpecificPage(webDriver);

        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriver.manage().window().maximize();

        webDriver.get("https://market.yandex.ru");

    }

    @Test
    public void marketTest() throws InterruptedException {

        mainPage.clickTownBtn();
        mainPage.inputTownField("Сан");
        mainPage.clickInputTownBtn();
        mainPage.clickCatalogBtn();
        mainPage.clickCompTechBtn();

        catalogPage.clickNotebookBtn();
        String notebookURL = webDriver.getCurrentUrl();

        Parser parser = new Parser();

        for(int i = 0; i < parser.getAllComputer().size(); i++) {

            Computer pc = parser.getAllComputer().get(i);
            System.out.println(pc.getManufacturerName());

            allCompPage.clickMoreBtn();
            allCompPage.inputManufBtn(pc.getManufacturerName());
            String minPrice = Integer.toString(pc.getMinPrice());
            String maxPrice = Integer.toString(pc.getMaxPrice());
            allCompPage.inputPrice(minPrice, maxPrice);
            allCompPage.clickFilterBtn();

            if (allCompPage.clickNotebookBtn()) {

                compPage.clickAllSpecific();

                specificPage.searchComputerParam(pc);

                webDriver.get(notebookURL);

                ArrayList<String> handles = new ArrayList<String>(webDriver.getWindowHandles());
                webDriver.switchTo().window(handles.get(0)).close();
                webDriver.switchTo().window(handles.get(1));
            } else {
                webDriver.get(notebookURL);
            }
        }

        parser.writeDocument();
        System.out.println("Ноутбук с максимальной диагональю: \n" + Computer.toString(Computer.maxDiagonal(parser.getAllComputer())));
    }

    @AfterClass
    public static void down(){
        webDriver.quit();
    }
}
