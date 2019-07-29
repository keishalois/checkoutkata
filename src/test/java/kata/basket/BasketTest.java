package kata.basket;

import kata.repo.OfferRepo;
import kata.sku.Sku;
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
        Sku sku = mock(Sku.class);
        when(sku.getNameOfProduct()).thenReturn("Apple");
        when(sku.getPrice()).thenReturn(50);

        Basket basketMock = new Basket();
        basketMock.addToBasket(sku);

        Assert.assertEquals(Integer.valueOf(1), basketMock.getBasketOfItems().get(sku));
    }

    @Test
    public void addMultipleItemsOfSameTypeToBasketShouldReturnMoreThanOne() {
        Sku sku = mock(Sku.class);
        when(sku.getNameOfProduct()).thenReturn("Apple");
        when(sku.getPrice()).thenReturn(50);

        Sku sku2 = mock(Sku.class);
        when(sku2.getNameOfProduct()).thenReturn("Pear");
        when(sku2.getPrice()).thenReturn(30);

        Basket basket2 = new Basket();
        basket2.addToBasket(sku);
        basket2.addToBasket(sku);
        Assert.assertEquals(Integer.valueOf(2), basket2.getBasketOfItems().get(sku));
        Assert.assertEquals(1, basket2.getBasketOfItems().size());
    }

    @Test
    public void addMultipleItemsOfDifferentTypesToBasketShouldReturnMultipleTypes() {
        Sku sku = mock(Sku.class);
        when(sku.getNameOfProduct()).thenReturn("Apple");
        when(sku.getPrice()).thenReturn(50);

        Sku sku2 = mock(Sku.class);
        when(sku2.getNameOfProduct()).thenReturn("Pear");
        when(sku2.getPrice()).thenReturn(30);

        Basket basket2 = new Basket();
        basket2.addToBasket(sku);
        basket2.addToBasket(sku2);
        Assert.assertEquals(Integer.valueOf(1), basket2.getBasketOfItems().get(sku));
        Assert.assertEquals(Integer.valueOf(1), basket2.getBasketOfItems().get(sku2));
        Assert.assertEquals(2, basket2.getBasketOfItems().size());
    }

}