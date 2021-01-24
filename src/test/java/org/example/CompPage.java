package org.example;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.File;
import java.io.IOException;

public class CompPage {

    public WebDriver webDriver;

    public CompPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
        this.webDriver = webDriver;
    }

    @FindBy(xpath = "//*[contains(text(), 'характеристики')]")
    private WebElement allSpecific;

    public void clickAllSpecific(){
        ((JavascriptExecutor) webDriver).executeScript("scroll(0,600)");
        allSpecific.click();
        screenShot();
    }
    public void screenShot(){

        File screenshot = ((TakesScreenshot) webDriver).
                getScreenshotAs(OutputType.FILE);
        String path = "src/test/resources/screenshots/" + screenshot.getName();

        try {
            FileUtils.copyFile(screenshot, new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
