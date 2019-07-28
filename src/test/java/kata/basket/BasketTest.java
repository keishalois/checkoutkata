package kata.basket;

import kata.repo.OfferRepo;
import kata.sku.Sku;
import org.junit.Assert;
import org.junit.Test;


import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)

public class BasketTest {

    @Test
    public void addSingleItemToBasketShouldReturnSingleItem() {
        final Sku apple = new Sku("apple",10);

        Basket basketMock = new Basket();
        basketMock.addToBasket(apple);
        Assert.assertEquals(Integer.valueOf(1), basketMock.getBasketOfItems().get(apple));
    }

    @Test
    public void addMultipleItemsOfSameTypeToBasketShouldReturnMoreThanOne() {
        final Sku apple = new Sku("apple",10);

        Basket basket2 = new Basket();
        basket2.addToBasket(apple);
        basket2.addToBasket(apple);
        Assert.assertEquals(Integer.valueOf(2), basket2.getBasketOfItems().get(apple));
        Assert.assertEquals(1, basket2.getBasketOfItems().size());
    }

    @Test
    public void addMultipleItemsOfDifferentTypesToBasketShouldReturnMultipleTypes() {
        final Sku apple = new Sku("apple",10);
        final Sku pear = new Sku("pear",12);
        Basket basket2 = new Basket();
        basket2.addToBasket(apple);
        basket2.addToBasket(pear);
        Assert.assertEquals(Integer.valueOf(1), basket2.getBasketOfItems().get(apple));
        Assert.assertEquals(Integer.valueOf(1), basket2.getBasketOfItems().get(pear));
        Assert.assertEquals(2, basket2.getBasketOfItems().size());
    }

}