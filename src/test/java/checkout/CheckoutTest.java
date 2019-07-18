package checkout;

import basket.Basket;
import sku.Sku;
import org.junit.Assert;
import org.junit.Test;

public class CheckoutTest {
        @Test
    public void singleItemTotalCostShouldCalcSingleItemValue() {
            //Arrange
            final Sku apple = new Sku("apple",10);
            Checkout checkout = new Checkout();
            Basket basket = new Basket();
            //Act
            basket.addToBasket(apple);
            //Asset
            Assert.assertEquals(10, checkout.basketCost(basket));
        }

        @Test
    public void multipleItemsOfSameTypeTotalCostShouldCalcAllItemsValue() {
        //Arrange
        final Sku apple = new Sku("apple",10);
        Checkout checkout = new Checkout();
        Basket basket1 = new Basket();
        //Act
        basket1.addToBasket(apple);
        basket1.addToBasket(apple);
        //Assert
        Assert.assertEquals(20, checkout.basketCost(basket1));
    }

        @Test
    public void multipleItemsOfDifferentTypeTotalCostShouldCalcAllItemsValue() {
        //Arrange
        final Sku apple = new Sku("apple",10);
        final Sku pear = new Sku("pear",5);
        Checkout checkout = new Checkout();
        Basket basket = new Basket();
        //Act
        basket.addToBasket(apple);
        basket.addToBasket(pear);
        //Assert
        Assert.assertEquals(15, checkout.basketCost(basket));
    }
}