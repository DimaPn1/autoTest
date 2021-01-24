package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CatalogPage {

    public WebDriver webDriver;

    public CatalogPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
        this.webDriver = webDriver;
    }

    @FindBy(xpath = "/html/body/div[1]/div[1]/div[3]/div/div/div/div/div[1]/div/div/div/div[2]/div[1]/div/div/a")
    private WebElement notebookBtn;

    public void clickNotebookBtn(){
        notebookBtn.click();
    }

}
