package offer;

import basket.Basket;
import checkout.Checkout;
import sku.Sku;
import org.junit.Assert;
import org.junit.Test;
import repo.OfferRepo;

public class OfferTest {
    @Test
    public void checkOfferAppliesToIndividualItemInCart(){
        //Arrange
        final Sku Apple = new Sku("Apple",50);
        final Sku Pear = new Sku("Pear",30);
        Checkout checkout = new Checkout();
        Basket basket = new Basket();
        //Act
        basket.addToBasket(Apple);
        basket.addToBasket(Apple);
        basket.addToBasket(Apple);
        basket.addToBasket(Pear);
        basket.addToBasket(Pear);
        //Assert
        Assert.assertEquals(210, checkout.basketCost(basket));
    }

}