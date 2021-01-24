package org.example;

import helper.Computer;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SpecificPage {

    public WebDriver webDriver;

    public SpecificPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
        this.webDriver = webDriver;
    }

    @FindBy(className = "b_cFcZl_zqNh")
    private WebElement listOfParam;

    public void searchComputerParam(Computer notebook) throws InterruptedException {

        //Поиск диагонали экрана

        WebElement diagonalValue = listOfParam.findElement(By.xpath("//*[contains(text(), 'дюймов')]"));
        double diagonal = Double.parseDouble(diagonalValue.getText().split(" ")[0]);
        notebook.setDiagonal(diagonal);
        System.out.println("Диагональ экрана: " + diagonal);

        //Поиск веса ноутбука

        WebElement weightValue = listOfParam.findElement(By.xpath("//*[contains(text(), 'кг')]"));
        double weight = Double.parseDouble(weightValue.getText().split(" ")[0]);
        notebook.setWeight(weight);
        System.out.println("Вес: " + weight);

    }
}
