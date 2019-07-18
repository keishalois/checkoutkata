package checkout;

import basket.Basket;
import repo.ItemRepo;
import repo.OfferRepo;
import sku.Sku;
import org.junit.Assert;
import org.junit.Test;

public class CheckoutServiceTest {
        @Test
    public void singleItemTotalCostShouldCalcSingleItemValue() {
            //Arrange
            final Sku apple = new Sku("apple",10);
            CheckoutService checkoutService = new CheckoutService();
            Basket basket = new Basket();
            //Act
            basket.addToBasket(apple);
            //Asset
            Assert.assertEquals(10, checkoutService.basketCost(basket));
        }

        @Test
    public void multipleItemsOfSameTypeTotalCostShouldCalcAllItemsValue() {
        //Arrange
        final Sku apple = new Sku("apple",10);
        CheckoutService checkoutService = new CheckoutService();
        Basket basket1 = new Basket();
        //Act
        basket1.addToBasket(apple);
        basket1.addToBasket(apple);
        //Assert
        Assert.assertEquals(20, checkoutService.basketCost(basket1));
    }

        @Test
    public void multipleItemsOfDifferentTypeTotalCostShouldCalcAllItemsValue() {
        //Arrange
        final Sku apple = new Sku("apple",10);
        final Sku pear = new Sku("pear",5);
        CheckoutService checkoutService = new CheckoutService();
        Basket basket = new Basket();
        //Act
        basket.addToBasket(apple);
        basket.addToBasket(pear);
        //Assert
        Assert.assertEquals(15, checkoutService.basketCost(basket));
    }

//    @Test
//    public void getSkuOfferReturnSkuPriceAfterDealApplied() {
//        //Arrange
//        OfferRepo repo = new OfferRepo();
//        ItemRepo repo2 = new ItemRepo();
//        Basket basket = new Basket();
//        final Sku Apple = new Sku("Apple",120);
//        final Sku Pear = new Sku("Pear",100);
//        final Sku Gin = new Sku("Gin", 1000 );
//        //Act
//        basket.addToBasket(Apple);
//        basket.addToBasket(Apple);
//        basket.addToBasket(Apple);
//        basket.addToBasket(Pear);
//        basket.addToBasket(Pear);
//        basket.addToBasket(Gin);
//        //Assert
//        Assert.assertEquals(130, repo.getSkuOffer(repo2, Apple, basket));
//        Assert.assertEquals(45, repo.getSkuOffer(repo2, Pear, basket));
//        Assert.assertEquals(1000, repo.getSkuOffer(repo2, Gin, basket));
//    }

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
    public void getSkuOfferRemainderProductNotInDealReturnsIntRemainder() {
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
    }
}