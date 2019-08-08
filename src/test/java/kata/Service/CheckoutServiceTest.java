package kata.Service;


import kata.Model.Basket;
import kata.Model.Offer;
import kata.Model.Sku;
import kata.Repository.OfferRepo;
import kata.Service.CheckoutService;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;

import java.util.HashMap;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)

public class CheckoutServiceTest {

    @Mock
    OfferRepo repo;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    public void calcBasketTotalShouldApplyOfferWhenOfferExists() {
        //Arrange
        Basket basket = new Basket();
        Sku appleSku = new Sku("Apple", 50);

        basket.addToBasket(appleSku);
        basket.addToBasket(appleSku);
        basket.addToBasket(appleSku);

        HashMap<String, Offer> offersForOfferRepo = new HashMap<>();
        Offer appleOffer = new Offer(3,130);
        offersForOfferRepo.put("Apple", appleOffer);
        CheckoutService checkoutService = new CheckoutService(repo);
        when(repo.getOffer(appleSku.getNameOfProduct())).thenReturn(Optional.of(appleOffer));

        //Act
        int total = checkoutService.checkoutBasket(basket);

        //Assert
        Assert.assertEquals(130, total);
    }

    @Test
    public void calcBasketTotalWhenItemOnOfferDoesNotQualifyForDiscountShouldNotApplyOffer(){
        //Arrange
        Basket basket = new Basket();
        Sku appleSku = new Sku("Apple", 50);

        basket.addToBasket(appleSku);
        basket.addToBasket(appleSku);

        HashMap<String, Offer> offersForOfferRepo = new HashMap<>();
        Offer appleOffer = new Offer(3,130);
        offersForOfferRepo.put("Apple", appleOffer);
        CheckoutService checkoutService = new CheckoutService(repo);
        when(repo.getOffer(appleSku.getNameOfProduct())).thenReturn(Optional.of(appleOffer));
        //Act
        int total = checkoutService.checkoutBasket(basket);

        //Assert
        Assert.assertEquals(100, total);
        verify(repo).getOffer(appleSku.getNameOfProduct());

    }

    @Test
    public void calcBasketTotalWhenItemHasOfferPlusExtraShouldReturnMixedPricing(){
        //Arrange
        Basket basket = new Basket();
        Sku appleSku = new Sku("Apple", 50);

        basket.addToBasket(appleSku);
        basket.addToBasket(appleSku);
        basket.addToBasket(appleSku);
        basket.addToBasket(appleSku);

        HashMap<String, Offer> offersForOfferRepo = new HashMap<>();
        Offer appleOffer = new Offer(3,130);
        offersForOfferRepo.put("Apple", appleOffer);
        CheckoutService checkoutService = new CheckoutService(repo);
        when(repo.getOffer(appleSku.getNameOfProduct())).thenReturn(Optional.of(appleOffer));

        //Act
        int total = checkoutService.checkoutBasket(basket);

        //Assert
        Assert.assertEquals(180, total);
    }

    @Test
    public void calcBasketTotalWhenItemHasNoOffersShouldReturnItemPrice() {
        //Arrange
        Basket basket = new Basket();
        Sku ginSku = new Sku("Gin", 1000);
        basket.addToBasket(ginSku);
        CheckoutService checkoutService = new CheckoutService(repo);
        when(repo.getOffer(ginSku.getNameOfProduct())).thenReturn(Optional.empty());

        //Act
        int total = checkoutService.checkoutBasket(basket);

        //Assert
        Assert.assertEquals(1000, total);
    }

    @Test
    public void calcBasketTotalWhenMixOfOfferItemsAndNonOfferItems(){
        //Arrange
        Basket basket = new Basket();
        Sku appleSku = new Sku("Apple", 50);
        Sku ginSku = new Sku("Gin", 1000);

        basket.addToBasket(appleSku);
        basket.addToBasket(appleSku);
        basket.addToBasket(appleSku);
        basket.addToBasket(appleSku);
        basket.addToBasket(ginSku);

        HashMap<String, Offer> offersForOfferRepo = new HashMap<>();
        Offer appleOffer = new Offer(3,130);
        offersForOfferRepo.put("Apple", appleOffer);

        CheckoutService checkoutService = new CheckoutService(repo);
        when(repo.getOffer(appleSku.getNameOfProduct())).thenReturn(Optional.of(appleOffer));
        when(repo.getOffer(ginSku.getNameOfProduct())).thenReturn(Optional.empty());

        //Act
        int total = checkoutService.checkoutBasket(basket);

        //Assert
        Assert.assertEquals(1180, total);
        verify(repo).getOffer(appleSku.getNameOfProduct());
        verify(repo).getOffer(ginSku.getNameOfProduct());
    }

}