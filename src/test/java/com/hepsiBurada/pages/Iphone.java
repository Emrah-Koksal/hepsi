package com.hepsiBurada.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class Iphone extends BasePage {

    @FindBy(css = "a.yPPu6UogPlaotjhx1Qki")
    public WebElement degerlendirmeler;

    @FindBy(xpath = "//div[.='Varsayılan']")
    public WebElement dropDown;

    @FindBy(xpath = "//div[.='En yeni değerlendirme']")
    public WebElement enYeniDegerlendirme;

    @FindBy(css = ".thumbsUp")
    public WebElement thumbsUp;

    @FindBy(css = ".hermes-ReviewCard-module-QA5PqdPP5EhkpY_vptfv")
    public List<WebElement> tesekkur;

    @FindBy(css = "div.XSkKHyNXPxm1YUa0z_sC")
    public WebElement digerSatıcılarAnaPencere;

    @FindBy(xpath = "//div[@data-test-id='price-current-price']")
    public List<WebElement> listedPrice;

    @FindBy(xpath = "//button[@data-test-id='addToCart']")
    public WebElement sepeteEkle;

    @FindBy(xpath = "//*[.='Ürüne git']")
    public List<WebElement> uruneGit;

    @FindBy(css = ".checkoutui-ProductOnBasketHeader-nOvp_U8bHbLzgKbSUFaz")
    public WebElement ürünSepetinizde;


    public Double getListedPrice(int index) {

        int indexOfCut = listedPrice.get(index).getText().length() - 4;
        String editedPriceText = listedPrice.get(index).getText().replace(".", "").replace(",", ".").substring(0, indexOfCut);
        return Double.valueOf(editedPriceText);

    }

}
