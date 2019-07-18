package sku;

import org.junit.Assert;
import org.junit.Test;

public class SkuTest {
    @Test
    public void checkSKUPriceReturnsIntSkuPrice() {
        //Arrange
        Sku banana = new Sku("banana", 3);
        //Assert
        Assert.assertEquals(3, banana.getPrice());
    }

    @Test
    public void checkSKUNameReturnsStringSKUName() {
        //Arrange
        Sku banana = new Sku("banana", 3);
        //Assert
        Assert.assertEquals("banana", banana.getNameOfProduct());
    }

}