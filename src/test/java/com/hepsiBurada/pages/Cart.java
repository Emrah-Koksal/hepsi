package com.hepsiBurada.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Cart extends BasePage {

    @FindBy(xpath = "//*[.='Sepete git']")
    public WebElement sepeteGit;


    @FindBy(id = "basket_payedPrice")
    public WebElement cartPrice;

    public Double getCartPrice() {

        int indexOfCut = cartPrice.getText().length() - 4;
        String editedPriceText = cartPrice.getText().replace(".", "").replace(",", ".").substring(0, indexOfCut);
        return Double.valueOf(editedPriceText);

    }
}
