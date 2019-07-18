package offer;

import basket.Basket;
import checkout.CheckoutService;
import sku.Sku;
import org.junit.Assert;
import org.junit.Test;

public class OfferTest {
    @Test
    public void checkOfferAppliesToIndividualItemInCart(){
        //Arrange
        final Sku Apple = new Sku("Apple",50);
        final Sku Pear = new Sku("Pear",30);
        CheckoutService checkoutService = new CheckoutService();
        Basket basket = new Basket();
        //Act
        basket.addToBasket(Apple);
        basket.addToBasket(Apple);
        basket.addToBasket(Apple);
        basket.addToBasket(Pear);
        basket.addToBasket(Pear);
        //Assert
        Assert.assertEquals(210, checkoutService.basketCost(basket));
    }

}