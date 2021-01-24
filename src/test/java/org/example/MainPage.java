package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage {

    public WebDriver webDriver;

    public MainPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
        this.webDriver = webDriver;
    }

    @FindBy(className = "_5ChREdcMew")
    private WebElement townBtn;

    @FindBy(xpath = "//*[@placeholder=\"Укажите другой регион\"]")
    private WebElement townField;

    @FindBy(xpath = "/html/body/div[13]/div/div/div/div/form/div[2]/div/a[1]/span")
    private WebElement popupWin;

    @FindBy(xpath = "/html/body/div[13]/div/div/div/div/form/div[2]/button")
    private WebElement inputTownBtn;

    @FindBy(xpath = "/html/body/div[2]/div[3]/noindex/div/div/div[2]/div[1]/div/button")
    private WebElement catalogBtn;

    @FindBy(xpath = "//*[@id=\"93758785\"]/div/div/div/div[1]/div/div/div/div/div/div/div/div[2]/div[1]/div/a")
    private WebElement compTechBtn;

    public void clickTownBtn(){
        townBtn.click();
    }

    public void inputTownField(String town){
        townField.sendKeys(town);
        popupWin.click();
    }

    public void clickInputTownBtn(){
        inputTownBtn.click();
    }

    public void clickCatalogBtn(){
        catalogBtn.click();
    }

    public void clickCompTechBtn(){
        compTechBtn.click();
    }
}
