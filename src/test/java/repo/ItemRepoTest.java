package repo;

import org.junit.Assert;
import org.junit.Test;
import sku.Sku;

public class ItemRepoTest {
    @Test
    public void itemRepoItemNamesReturnsSetOfSkus() {
        //Arrange
        ItemRepo repo = new ItemRepo();
        //Act
        System.out.println(repo.getAllSkuItemsAsSet());
    }

    @Test
    public void itemRepoGetNameOfSKUReturnsStringSKUName() {
        //Arrange
        ItemRepo repo = new ItemRepo();
        final Sku Apple = new Sku("Apple",120);
        final Sku Pear = new Sku("Pear",100);
        //Assert
        Assert.assertEquals("Apple", repo.getParticularSkuName(Apple));
        Assert.assertEquals("Pear", repo.getParticularSkuName(Pear));
    }

    @Test
    public void itemRepoNonExistentSKUReturnsStringNoMatch() {
        //Arrange
        ItemRepo repo = new ItemRepo();
        final Sku Banana = new Sku("Banana", 100);
        //Assert
        Assert.assertEquals("No match", repo.getParticularSkuName(Banana));
    }

     @Test
     public void itemRepoGetPriceOfSKUReturnsIntSKUPrice() {
         //Arrange
        ItemRepo repo = new ItemRepo();
        final Sku Apple = new Sku("Apple",120);
        final Sku Pear = new Sku("Pear",100);
        //Assert
        Assert.assertEquals(120, repo.getParticularSkuPrice(Apple));
        Assert.assertEquals(100, repo.getParticularSkuPrice(Pear));
    }

    @Test
    public void itemRepoNonExistentSKUReturnsFreePrice() {
        //Arrange
        ItemRepo repo = new ItemRepo();
        final Sku Rum = new Sku("Rum", 1500);
        //Assert
        Assert.assertEquals(0, repo.getParticularSkuPrice(Rum));
    }

}