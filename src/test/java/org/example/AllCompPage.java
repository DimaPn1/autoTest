package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class AllCompPage {

    public WebDriver webDriver;

    public AllCompPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
        this.webDriver = webDriver;
    }

    @FindBy(className = "b_3M-BxXXLT-")
    private WebElement moreBtn;

    @FindBy(className = "b_TaGwB0gTvr")
    private WebElement minAndMaxPrice;

    @FindBy(className = "b_1nBAOXvInm")
    private WebElement filterBtn;

    public void clickMoreBtn(){
        moreBtn.click();
    }

    public void inputManufBtn(String name){

        List<WebElement> manufacturer = webDriver.findElements(By.className("b_1LZT0xNcJk"));
        for (WebElement webElement : manufacturer) {
            if(webElement.getText().equals(name)){
                webElement.click();
                break;
            }
        }
    }

    public void inputPrice(String min, String max){
        List<WebElement> prices = minAndMaxPrice.findElements(By.cssSelector("input.b_2JDvXzYsUI"));
        prices.get(0).sendKeys(min);
        prices.get(1).sendKeys(max);
    }

    public void clickFilterBtn(){
        filterBtn.click();
        List<WebElement> list = webDriver.findElements(By.className("b_1nBAOXvInm"));
        list.get(4).click();
    }

    public boolean clickNotebookBtn()  {
        try {
            Thread.sleep(2000);
            List<WebElement> list = webDriver.findElements(By.className("b_3l6BMMP8pQ"));
            if(list.size() == 0){
                return false;
            }else{
                List<WebElement> computer = list.get(0).findElements(By.className("b_3I98ORiKKK"));
                if(computer.size() >= 6) {
                    computer.get(6).click();
                }else{
                    computer.get(computer.size() - 1).click();
                }
                nextPage();
                return true;
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void nextPage(){
        ArrayList<String> handles = new ArrayList<String>(webDriver.getWindowHandles());
        webDriver.switchTo().window(handles.get(1));
    }
}
