package com.hepsiBurada.step_definitions;

import com.hepsiBurada.pages.Cart;
import com.hepsiBurada.pages.Elektronik;
import com.hepsiBurada.pages.Iphone;
import com.hepsiBurada.utilities.BrowserUtils;
import com.hepsiBurada.utilities.Driver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.eo.Do;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NavigateToWebSite {

    Elektronik elektronik = new Elektronik();
    Iphone iphone = new Iphone();
    Cart cart = new Cart();
    public WebDriverWait wait = new WebDriverWait(Driver.get(), Duration.ofSeconds(30));


    @When("Search for iphones and select a random product")
    public void searchForIphonesAndSelectARandomProduct() {
        iphone.acceptCookies();
        BrowserUtils.waitFor(2);

        elektronik.searchBox.click();
        BrowserUtils.waitFor(2);

        elektronik.searchBoxInput.sendKeys("iphone" + Keys.ENTER);
        BrowserUtils.waitFor(2);

        elektronik.selectRandomIphone();
        BrowserUtils.waitForPageToLoad();

        BrowserUtils.switchToWindow();
        System.out.println("Driver.get().getTitle() = " + Driver.get().getTitle());

    }

    boolean degerlendirmeExist = true;

    @And("Hit değerlendirmeler and Rank according to En Yeni Değerlendirme give thumbsUp")
    public void hitDeğerlendirmelerAndRankAccordingToEnYeniDeğerlendirmeGiveThumbsUp() {

        try {
            System.out.println("Before clicking değerlendirmeler");
            iphone.degerlendirmeler.click();
            System.out.println("After clicking değerlendirmeler");

            System.out.println("Before scrolling to dropdown");
            BrowserUtils.scrollToElement(iphone.dropDown);
            System.out.println("After scrolling to dropdown");

            BrowserUtils.waitFor(2);

            iphone.dropDown.click();
            iphone.enYeniDegerlendirme.click();

            BrowserUtils.scrollToElement(iphone.thumbsUp);
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
        BrowserUtils.waitFor(5);
        System.out.println("iphone.ürünSepetinizde.getText() = " + iphone.ürünSepetinizde.getText());

        Assert.assertEquals("ürün sepetinizde listelenmedi", "Ürün sepetinizde", iphone.ürünSepetinizde.getText());
    }


    @When("deneme denemeler")
    public void denemeDenemeler() {
        try {
            System.out.println("Before clicking değerlendirmeler");
            iphone.degerlendirmeler.click();
            System.out.println("After clicking değerlendirmeler");

            System.out.println("Before scrolling to dropdown");
            BrowserUtils.scrollToElement(iphone.dropDown);
            System.out.println("After scrolling to dropdown");

            BrowserUtils.waitFor(2);

            iphone.dropDown.click();
            iphone.enYeniDegerlendirme.click();

            BrowserUtils.scrollToElement(iphone.thumbsUp);
            iphone.thumbsUp.click();
        } catch (NoSuchElementException e) {
            degerlendirmeExist = false;
        }
    }

    Double productPrice = 0.0;

    @And("Store the price from the selected product")
    public void storeThePriceFromTheSelectedProduct() {
        productPrice = iphone.getListedPrice(0);
        BrowserUtils.waitFor(2);
        BrowserUtils.scrollToElement(iphone.sepeteEkle);
        iphone.sepeteEkle.click();
        BrowserUtils.waitFor(2);

    }

    @And("Add product to cart")
    public void addProductToCart() {
        System.out.println("add to product step");
        BrowserUtils.waitFor(5);
        cart.sepeteGit.click();
        BrowserUtils.waitFor(2);
    }


    @Then("Verify price from product page matches price from cart")
    public void verifyPriceFromProductPageMatchesPriceFromCart() {
        BrowserUtils.waitFor(2);
        double cartPrice = cart.getCartPrice();
        System.out.println("cartPrice = " + cartPrice);
        System.out.println("productPrice = " + productPrice);

        Assert.assertEquals(productPrice, cartPrice, 0.0);

    }
}
