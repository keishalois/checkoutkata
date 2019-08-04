package kata.Model.basket;

import kata.Model.sku.Sku;
import org.junit.Assert;
import org.junit.Test;


import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)

public class BasketTest {

    @Test
    public void addSingleItemToBasketShouldReturnSingleItem() {
        //Arrange
        Sku appleSku = new Sku("Apple", 50);
        //Act
        Basket basket = new Basket();
        basket.addToBasket(appleSku);
        System.out.println(basket.showBasket());
        //Assert
        Assert.assertEquals(Integer.valueOf(1), basket.getBasketOfItems().get(appleSku));
    }

    @Test
    public void addMultipleItemsOfSameTypeToBasketShouldReturnMoreThanOne() {
        //Arrange
        Sku appleSku = new Sku("Apple", 50);
        //Act
        Basket basket2 = new Basket();
        basket2.addToBasket(appleSku);
        basket2.addToBasket(appleSku);
        System.out.println(basket2.showBasket());
        //Assert
        Assert.assertEquals(Integer.valueOf(2), basket2.getBasketOfItems().get(appleSku));
        Assert.assertEquals(1, basket2.getBasketOfItems().size());
    }

    @Test
    public void addMultipleItemsOfDifferentTypesToBasketShouldReturnMultipleTypes() {
        //Arrange
        Sku appleSku = new Sku("Apple", 50);
        Sku pearSku = new Sku("Pear", 30);
        //Act
        Basket basket2 = new Basket();
        basket2.addToBasket(appleSku);
        basket2.addToBasket(pearSku);
        System.out.println(basket2.showBasket());
        //Assert
        Assert.assertEquals(Integer.valueOf(1), basket2.getBasketOfItems().get(appleSku));
        Assert.assertEquals(Integer.valueOf(1), basket2.getBasketOfItems().get(pearSku));
        Assert.assertEquals(2, basket2.getBasketOfItems().size());
    }

}