package repo;

import basket.Basket;
import offer.Offer;
import org.junit.Assert;
import org.junit.Test;
import sku.Sku;

import static org.junit.Assert.*;

public class OfferRepoTest {

    @Test
    public void getOffersReturnsOffers() {
        OfferRepo repo = new OfferRepo();
        System.out.println(repo.getOffers());
    }

    @Test
    public void getSkuOfferReturnSkuPriceAfterDealApplied() {
        //Arrange
        OfferRepo repo = new OfferRepo();
        ItemRepo repo2 = new ItemRepo();
        Basket basket = new Basket();
        final Sku Apple = new Sku("Apple",120);
        final Sku Pear = new Sku("Pear",100);
        final Sku Gin = new Sku("Gin", 1000 );
        //Act
        basket.addToBasket(Apple);
        basket.addToBasket(Apple);
        basket.addToBasket(Apple);
        basket.addToBasket(Pear);
        basket.addToBasket(Pear);
        basket.addToBasket(Gin);
        //Assert
        Assert.assertEquals(130, repo.getSkuOffer(repo2, Apple, basket));
        Assert.assertEquals(45, repo.getSkuOffer(repo2, Pear, basket));
        Assert.assertEquals(1000, repo.getSkuOffer(repo2, Gin, basket));
    }

    @Test
    public void getSkuOfferMultiplesOfReturnsIntMultipleCount() {
        OfferRepo repo = new OfferRepo();
        ItemRepo repo2 = new ItemRepo();
        Basket basket = new Basket();
        final Sku Apple = new Sku("Apple",120);
        final Sku Pear = new Sku("Pear",100);
        final Sku Gin = new Sku("Gin", 1000 );
        //Act
        basket.addToBasket(Apple);
        basket.addToBasket(Apple);
        basket.addToBasket(Apple);
        basket.addToBasket(Apple);
        basket.addToBasket(Pear);
        basket.addToBasket(Pear);
        basket.addToBasket(Gin);
        //Assert
        Assert.assertEquals(1, repo.getSkuOfferMultiples(repo2, Apple, basket));
    }

    @Test
    public void getSkuOfferRemainderReturnsIntRemainder() {
        OfferRepo repo = new OfferRepo();
        Basket basket = new Basket();
        final Sku Apple = new Sku("Apple",120);
        final Sku Pear = new Sku("Pear",100);
        final Sku Gin = new Sku("Gin", 1000 );
        //Act
        basket.addToBasket(Apple);
        basket.addToBasket(Apple);
        basket.addToBasket(Apple);
        basket.addToBasket(Apple);
        basket.addToBasket(Pear);
        basket.addToBasket(Pear);
        basket.addToBasket(Gin);
        //Assert
        Assert.assertEquals(1, repo.getSkuOfferRemainders(Apple,basket));
    }
}