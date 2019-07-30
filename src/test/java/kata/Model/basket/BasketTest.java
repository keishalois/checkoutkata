package kata.Model.basket;

import kata.Model.sku.Sku;
import org.junit.Assert;
import org.junit.Test;


import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)

public class BasketTest {

    @Test
    public void addSingleItemToBasketShouldReturnSingleItem() {
        //Arrange
        Sku sku = mock(Sku.class);
        when(sku.getNameOfProduct()).thenReturn("Apple");
        when(sku.getPrice()).thenReturn(50);
        //Act
        Basket basketMock = new Basket();
        basketMock.addToBasket(sku);
        System.out.println(basketMock.showBasket());
        //Assert
        Assert.assertEquals(Integer.valueOf(1), basketMock.getBasketOfItems().get(sku));
    }

    @Test
    public void addMultipleItemsOfSameTypeToBasketShouldReturnMoreThanOne() {
        //Arrange
        Sku sku = mock(Sku.class);
        when(sku.getNameOfProduct()).thenReturn("Apple");
        when(sku.getPrice()).thenReturn(50);

        Sku sku2 = mock(Sku.class);
        when(sku2.getNameOfProduct()).thenReturn("Pear");
        when(sku2.getPrice()).thenReturn(30);
        //Act
        Basket basket2 = new Basket();
        basket2.addToBasket(sku);
        basket2.addToBasket(sku);
        System.out.println(basket2.showBasket());
        //Assert
        Assert.assertEquals(Integer.valueOf(2), basket2.getBasketOfItems().get(sku));
        Assert.assertEquals(1, basket2.getBasketOfItems().size());
    }

    @Test
    public void addMultipleItemsOfDifferentTypesToBasketShouldReturnMultipleTypes() {
        //Arrange
        Sku sku = mock(Sku.class);
        when(sku.getNameOfProduct()).thenReturn("Apple");
        when(sku.getPrice()).thenReturn(50);

        Sku sku2 = mock(Sku.class);
        when(sku2.getNameOfProduct()).thenReturn("Pear");
        when(sku2.getPrice()).thenReturn(30);
        //Act
        Basket basket2 = new Basket();
        basket2.addToBasket(sku);
        basket2.addToBasket(sku2);
        System.out.println(basket2.showBasket());
        //Assert
        Assert.assertEquals(Integer.valueOf(1), basket2.getBasketOfItems().get(sku));
        Assert.assertEquals(Integer.valueOf(1), basket2.getBasketOfItems().get(sku2));
        Assert.assertEquals(2, basket2.getBasketOfItems().size());
    }

}