package com.hepsiBurada.step_definitions;

import com.hepsiBurada.pages.Cart;
import com.hepsiBurada.pages.Iphone;
import com.hepsiBurada.utilities.BrowserUtils;
import com.hepsiBurada.utilities.Driver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class UiSteps {

    Iphone iphone = new Iphone();
    Cart cart = new Cart();
    public WebDriverWait wait = new WebDriverWait(Driver.get(), Duration.ofSeconds(30));


    @When("Search for iphones and select a random product")
    public void searchForIphonesAndSelectARandomProduct() {
        iphone.acceptCookies();
        BrowserUtils.waitFor(3);

        iphone.searchBox.click();
        BrowserUtils.waitFor(2);

        iphone.searchBoxInput.sendKeys("iphone" + Keys.ENTER);
        BrowserUtils.waitForPageToLoad();

        iphone.selectRandomIphone();
        BrowserUtils.waitForPageToLoad();

        BrowserUtils.switchToWindow();
        System.out.println("Driver.get().getTitle() = " + Driver.get().getTitle());

    }

    boolean degerlendirmeExist = true;

    @And("Hit değerlendirmeler and Rank according to En Yeni Değerlendirme give thumbsUp")
    public void hitDeğerlendirmelerAndRankAccordingToEnYeniDeğerlendirmeGiveThumbsUp() {

        try {
            BrowserUtils.waitForVisibility(iphone.degerlendirmeler, Duration.ofSeconds(2));
            iphone.degerlendirmeler.click();

            BrowserUtils.scrollToElement(iphone.dropDown);
            BrowserUtils.waitForVisibility(iphone.dropDown, Duration.ofSeconds(2));
            iphone.dropDown.click();

            BrowserUtils.waitForVisibility(iphone.enYeniDegerlendirme, Duration.ofSeconds(2));
            iphone.enYeniDegerlendirme.click();

            BrowserUtils.scrollToElement(iphone.thumbsUp);
            BrowserUtils.waitForVisibility(iphone.thumbsUp, Duration.ofSeconds(2));
            iphone.thumbsUp.click();
        } catch (NoSuchElementException e) {
            degerlendirmeExist = false;
        }

    }

    @Then("Verify success message as {string}")
    public void verifySuccessMessageAs(String expectedTesekkur) {

        if (degerlendirmeExist) {
            Assert.assertTrue(iphone.tesekkur.size() == 1);
            Assert.assertEquals("Teşekkür Ederiz is not displayed", expectedTesekkur, iphone.tesekkur.get(0).getText());
        }
    }

    @Then("Verify Diğer Satıcılar exists on the page")
    public void verifyDiğerSatıcılarExistsOnThePage() {
        Assert.assertTrue(iphone.digerSatıcılarAnaPencere.isDisplayed());
    }

    @When("Compare prices and select the cheapest one")
    public void comparePricesAndSelectTheCheapestOne() {
        int index = 0;
        double lowestPrice = Double.MAX_VALUE;

        for (int i = 0; i < iphone.listedPrice.size(); i++) {

            double price = iphone.getListedPrice(i);

            if (price < lowestPrice) {
                index = i;
                lowestPrice = price;
            }
        }
        System.out.println("The lowest price is: " + lowestPrice);

        if (index > 0) {
            iphone.uruneGit.get(index - 1).click();
        }
        iphone.sepeteEkle.click();
    }

    @Then("verify product is added to cart")
    public void verifyProductIsAddedToCart() {
        BrowserUtils.waitForVisibility(iphone.ürünSepetinizde, Duration.ofSeconds(5));
        System.out.println("iphone.ürünSepetinizde.getText() = " + iphone.ürünSepetinizde.getText());
        Assert.assertEquals("ürün sepetinizde listelenmedi", "Ürün sepetinizde", iphone.ürünSepetinizde.getText());
    }

    Double productPrice = 0.0;

    @And("Store the price from the selected product")
    public void storeThePriceFromTheSelectedProduct() {
        productPrice = iphone.getListedPrice(0);

        BrowserUtils.scrollToElement(iphone.sepeteEkle);
        BrowserUtils.waitForVisibility(iphone.sepeteEkle, Duration.ofSeconds(2));
        iphone.sepeteEkle.click();

    }

    @And("Add product to cart")
    public void addProductToCart() {
        System.out.println("add product to cart stepDef");
        BrowserUtils.waitForVisibility(cart.sepeteGit, Duration.ofSeconds(5));
        cart.sepeteGit.click();
    }


    @Then("Verify price from product page matches price from cart")
    public void verifyPriceFromProductPageMatchesPriceFromCart() {
        BrowserUtils.waitForVisibility(cart.cartPrice, Duration.ofSeconds(5));
        double cartPrice = cart.getCartPrice();
        System.out.println("cartPrice = " + cartPrice);
        System.out.println("productPrice = " + productPrice);

        Assert.assertEquals(productPrice, cartPrice, 0.0);

    }
}
