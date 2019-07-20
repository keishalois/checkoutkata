package checkout;

import basket.Basket;
import repo.OfferRepo;
import sku.Sku;
import org.junit.Assert;
import org.junit.Test;

public class CheckoutServiceTest {

    @Test
    public void getHowManyDealsToApplyToCheckoutReturnsIntMultipleCount() {
        OfferRepo repo = new OfferRepo();
        Basket basket = new Basket();
        CheckoutService checkoutService = new CheckoutService();
        final Sku Apple = new Sku("Apple",120);
        final Sku Pear = new Sku("Pear",100);
        final Sku Gin = new Sku("Gin", 1000 );
        //Act
        basket.addToBasket(Apple);
        basket.addToBasket(Apple);
        basket.addToBasket(Apple);
        basket.addToBasket(Apple);
        basket.addToBasket(Apple);
        basket.addToBasket(Apple);
        basket.addToBasket(Pear);
        basket.addToBasket(Pear);
        basket.addToBasket(Gin);
        //Assert
        Assert.assertEquals(2, checkoutService.getSkuOfferMultiples(repo, Apple, basket));
        Assert.assertEquals(1, checkoutService.getSkuOfferMultiples(repo, Pear, basket));
        Assert.assertEquals(0, checkoutService.getSkuOfferMultiples(repo, Gin, basket));
    }

    @Test
    public void getIndividualProductNumberNotInDealReturnsIntRemainder() {
        OfferRepo repo = new OfferRepo();
        Basket basket = new Basket();
        CheckoutService checkoutService = new CheckoutService();
        final Sku Apple = new Sku("Apple",120);
        final Sku Pear = new Sku("Pear",100);
        final Sku Gin = new Sku("Gin", 1000 );
        //Act
        basket.addToBasket(Apple);
        basket.addToBasket(Apple);
        basket.addToBasket(Apple);
        basket.addToBasket(Apple);
        basket.addToBasket(Apple);
        basket.addToBasket(Pear);
        basket.addToBasket(Pear);
        basket.addToBasket(Gin);
        //Assert
        Assert.assertEquals(2, checkoutService.getSkuOfferRemainders(repo, Apple,basket));
        Assert.assertEquals(0, checkoutService.getSkuOfferRemainders(repo, Pear,basket));
        //gin returns 0 because it does not have any deals associated with it
        Assert.assertEquals(0, checkoutService.getSkuOfferRemainders(repo, Gin,basket));
    }

    @Test
    public void calcCostsWithDealsAppliedAndIndividualItemsReturnsIntTotalPrice() {
        OfferRepo repo = new OfferRepo();
        Basket basket = new Basket();
        CheckoutService checkoutService = new CheckoutService();
        //      Apple offers: 3 for 130p;
        //      Pear offers: 2 for 45p;
        final Sku Apple = new Sku("Apple",50);
        final Sku Pear = new Sku("Pear",30);
        final Sku Gin = new Sku("Gin", 1000 );

        //Act
        basket.addToBasket(Apple);
        basket.addToBasket(Apple);
        basket.addToBasket(Apple);
        //deal applied so cost of 3 apples = 130p
        basket.addToBasket(Apple);
        basket.addToBasket(Apple);
        // cost of 2 apples = 100p
        basket.addToBasket(Pear);
        basket.addToBasket(Pear);
        // deal applied so cost of 2 pears = 45p
        basket.addToBasket(Gin);
        // no deals for gin so cost = 1000p

        //Assert
        Assert.assertEquals(1275, checkoutService.checkoutBasket(repo, basket));
    }
}