package kata.checkout;

import kata.basket.Basket;
import kata.repo.OfferRepo;
import kata.sku.Sku;
import org.junit.Assert;
import org.junit.Test;

public class CheckoutServiceTest {


//    @Test
//    public void calcCostsWithDealsAppliedAndIndividualItemsReturnsIntTotalPrice() {
//        OfferRepo repo = new OfferRepo();
//        Basket basket = new Basket();
//        kata.checkout.CheckoutService checkoutService = new kata.checkout.CheckoutService(repo);
//        //      Apple offers: 3 for 130p;
//        //      Pear offers: 2 for 45p;
//        final Sku Apple = new Sku("Apple",50);
//        final Sku Pear = new Sku("Pear",30);
//        final Sku Gin = new Sku("Gin", 1000 );
//
//        //Act
//        basket.addToBasket(Apple);
//        basket.addToBasket(Apple);
//        basket.addToBasket(Apple);
//        //deal applied so cost of 3 apples = 130p
//        basket.addToBasket(Apple);
//        basket.addToBasket(Apple);
//        // cost of 2 apples = 100p
//        basket.addToBasket(Pear);
//        basket.addToBasket(Pear);
//        // deal applied so cost of 2 pears = 45p
//        basket.addToBasket(Gin);
//        // no deals for gin so cost = 1000p
//
//        //Assert
//        Assert.assertEquals(1275, checkoutService.checkoutBasket(basket));
//    }
}