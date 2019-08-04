package kata.Model.checkout;

import kata.Model.basket.Basket;
import kata.Model.offer.Offer;
import kata.Model.repo.ItemRepo;
import kata.Model.repo.OfferRepo;
import kata.Model.sku.Sku;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;

import java.util.HashMap;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)

public class CheckoutServiceTest {

    @Mock
    OfferRepo repo;

    @Mock
    ItemRepo itemRepo;

    //this creates the mock offer repo and mock item repo in the test class allowing you to instantiate later
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    //test mock item repo works
    @Test
    public void itemRepoMockAddsSkusShouldReturnNotNullMockItemRepo() {
        //Arrange
        HashMap<String,Sku> skusForItemRepo = new HashMap<>();
        Basket basket = new Basket();
        Sku Apple = new Sku("Apple",20);
        skusForItemRepo.put("Apple", Apple);
        when(itemRepo.getAllSkus()).thenReturn(skusForItemRepo);
        when(itemRepo.getSku("Apple")).thenReturn(Apple);
        Sku appleSku = itemRepo.getSku("Apple");
        //Act
        basket.addToBasket(appleSku);
        basket.addToBasket(appleSku);
        basket.addToBasket(appleSku);
        //Assert
        Assert.assertEquals(skusForItemRepo, itemRepo.getAllSkus());
        Assert.assertEquals(Apple,appleSku);
    }

    //test mock offer repo works
    @Test
    public void getOffersForItemShouldReturnOffers() {
        HashMap<String, Offer> offersForOfferRepo = new HashMap<>();
        Sku appleSku = new Sku("Apple", 50);
        Offer appleOffer = new Offer(3,130);
        offersForOfferRepo.put("Apple", appleOffer);
        when(repo.getOffer(appleSku.getNameOfProduct())).thenReturn(appleOffer);
        Assert.assertEquals(offersForOfferRepo.get(appleSku.getNameOfProduct()).getPriceInPence(), repo.getOffer(appleSku.getNameOfProduct()).getPriceInPence());
    }

    //test checkout works - this test is still a work in progress as it doesnt process the offers :)
    @Test
    public void calcOffersTotalMockShouldReturnIntTotalOffer() {
        //Arrange
        HashMap<String,Sku> skusForItemRepo = new HashMap<>();
        HashMap<String, Offer> offersForOfferRepo = new HashMap<>();
        Sku appleSku = new Sku("Apple", 50);
        Offer appleOffer = new Offer(3,130);

        Basket basket = new Basket();
        skusForItemRepo.put("Apple", appleSku);
        offersForOfferRepo.put("Apple", appleOffer);
        //instantiate offer repo mock when create checkout
        when(repo.getOffer(appleSku.getNameOfProduct())).thenReturn(appleOffer);
        CheckoutService checkoutService = new CheckoutService(repo);
        when(itemRepo.getAllSkus()).thenReturn(skusForItemRepo);
        when(itemRepo.getSku("Apple")).thenReturn(appleSku);
        //Act
        basket.addToBasket(appleSku);
        basket.addToBasket(appleSku);
        basket.addToBasket(appleSku);
        //Assert
        Assert.assertEquals(130, checkoutService.checkoutBasket(basket));
    }

    //this test still needs rewriting to use mock repos
    @Test
    public void calcCostOfItemCheckoutShouldReturnIntOfMixOfOffersAndNonOffersTotal() {
        //Arrange
        Basket basket = new Basket();

        kata.Model.checkout.CheckoutService checkoutService = new kata.Model.checkout.CheckoutService(repo);

        //Act

        basket.addToBasket(itemRepo.getSku("Apple"));
        basket.addToBasket(itemRepo.getSku("Apple"));
        basket.addToBasket(itemRepo.getSku("Apple"));
        basket.addToBasket(itemRepo.getSku("Apple"));

        basket.addToBasket(itemRepo.getSku("Pear"));
        basket.addToBasket(itemRepo.getSku("Pear"));

        System.out.println(basket.showBasket());
        //Assert
        Assert.assertEquals(225, checkoutService.checkoutBasket(basket));
    }

}