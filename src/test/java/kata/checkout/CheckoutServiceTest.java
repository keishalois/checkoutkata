package kata.checkout;

import kata.basket.Basket;
import kata.offer.Offer;
import kata.repo.ItemRepo;
import kata.repo.OfferRepo;
import kata.sku.Sku;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)

public class CheckoutServiceTest {

    @InjectMocks
    OfferRepo repo;

    @Mock
    ItemRepo itemRepo;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    public void calcCostOfItemCheckoutShouldReturnIntOfMixOfOffersAndNonOffersTotal() {
        Sku sku = mock(Sku.class);
        when(sku.getNameOfProduct()).thenReturn("Apple");
        when(sku.getPrice()).thenReturn(50);

        Sku sku2 = mock(Sku.class);
        when(sku2.getNameOfProduct()).thenReturn("Pear");
        when(sku2.getPrice()).thenReturn(30);

        Offer offer = mock(Offer.class);
        when(offer.getQuantityOfProduct()).thenReturn(3);
        when(offer.getPriceInPence()).thenReturn(130);
        repo.getOffers().put(sku.getNameOfProduct(), offer);
//        when(repo.getOffer(sku.getNameOfProduct())).thenReturn(offer);


        itemRepo.getAllSkus().put(sku.getNameOfProduct(), sku);

        Basket basket = new Basket();

        kata.checkout.CheckoutService checkoutService = new kata.checkout.CheckoutService(repo);

        basket.addToBasket(sku);
        basket.addToBasket(sku);
        basket.addToBasket(sku);
        basket.addToBasket(sku);

        basket.addToBasket(sku2);
        basket.addToBasket(sku2);

        Assert.assertEquals(225, checkoutService.checkoutBasket(basket));
    }

//    @Test
//    public void calcCostsWithDealsAppliedAndIndividualItemsReturnsIntTotalPrice() {
////        OfferRepo repo = new OfferRepo();
////        ItemRepo itemRepo = new ItemRepo();
//        Offer offer = mock(Offer.class);
//        when(offer.getQuantityOfProduct()).thenReturn(3);
//        when(offer.getPriceInPence()).thenReturn(50);
//
//        Sku sku = mock(Sku.class);
//        Basket basket = new Basket();
//        kata.checkout.CheckoutService checkoutService = new kata.checkout.CheckoutService(repo);
//        //      Apple offers: 3 for 130p;
//        //      Pear offers: 2 for 45p;
////        final Sku Apple = new Sku("Apple",50);
////        final Sku Pear = new Sku("Pear",30);
////        final Sku Gin = new Sku("Gin", 1000 );
//
//        //Act
//        basket.addToBasket(itemRepo.getSku("Apple"));
//        basket.addToBasket(itemRepo.getSku("Apple"));
//        basket.addToBasket(itemRepo.getSku("Apple"));
////        basket.addToBasket(Apple);
////        basket.addToBasket(Apple);
////        basket.addToBasket(Apple);
//        //deal applied so cost of 3 apples = 130p
//        basket.addToBasket(itemRepo.getSku("Apple"));
//        basket.addToBasket(itemRepo.getSku("Apple"));
//
////        basket.addToBasket(Apple);
////        basket.addToBasket(Apple);
//        // cost of 2 apples = 100p
//        basket.addToBasket(itemRepo.getSku("Pear"));
//        basket.addToBasket(itemRepo.getSku("Pear"));
//
////        basket.addToBasket(Pear);
////        basket.addToBasket(Pear);
//        // deal applied so cost of 2 pears = 45p
//        basket.addToBasket(itemRepo.getSku("Gin"));
//
////        basket.addToBasket(Gin);
//        // no deals for gin so cost = 1000p
//
//        //Assert
//        Assert.assertEquals(1275, checkoutService.checkoutBasket(basket));
//    }
}