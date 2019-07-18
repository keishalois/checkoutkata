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
        System.out.println(repo.getSkus());
    }

}