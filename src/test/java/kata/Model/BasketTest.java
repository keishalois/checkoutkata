package kata.Model;

import org.junit.*;


import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BasketTest {
    @Before
    public void before() {
        System.out.println("Before");
    }
    @After
    public void after() {
        System.out.println("After");
    }
    @BeforeClass
    public static void beforeClass() {
        System.out.println("Before Class");
    }
    @AfterClass
    public static void afterClass() {
        System.out.println("After Class");
    }

    @Test
    public void addSingleItemToBasketShouldReturnSingleItem() {
        System.out.println("Test1");
        //Arrange
        Sku appleSku = new Sku("Apple", 50);
        Basket basket = new Basket();
        basket.addToBasket(appleSku);
        System.out.println(basket.showBasket());
        //Assert
        Assert.assertEquals(Integer.valueOf(1), basket.getBasketOfItems().get(appleSku));
    }

    @Test
    public void addMultipleItemsOfSameTypeToBasketShouldReturnMoreThanOne() {
        System.out.println("Test2");

        //Arrange
        Sku appleSku = new Sku("Apple", 50);
        Basket basket = new Basket();
        basket.addToBasket(appleSku);
        basket.addToBasket(appleSku);
        System.out.println(basket.showBasket());
        //Assert
        Assert.assertEquals(Integer.valueOf(2), basket.getBasketOfItems().get(appleSku));
        Assert.assertEquals(1, basket.getBasketOfItems().size());
    }

    @Test
    public void addMultipleItemsOfDifferentTypesToBasketShouldReturnMultipleTypes() {
        System.out.println("Test3");

        //Arrange
        Sku appleSku = new Sku("Apple", 50);
        Sku pearSku = new Sku("Pear", 30);
        //Act
        Basket basket = new Basket();
        basket.addToBasket(appleSku);
        basket.addToBasket(pearSku);
        System.out.println(basket.showBasket());
        //Assert
        Assert.assertEquals(Integer.valueOf(1), basket.getBasketOfItems().get(appleSku));
        Assert.assertEquals(Integer.valueOf(1), basket.getBasketOfItems().get(pearSku));
        Assert.assertEquals(2, basket.getBasketOfItems().size());
    }

    @Test
    public void removeAllItemsFromBasket(){
        System.out.println("Test4");

        //Arrange
        Sku appleSku = new Sku("Apple", 50);
        Sku pearSku = new Sku("Pear", 30);
        //Act
        Basket basket = new Basket();
        basket.addToBasket(appleSku);
        basket.addToBasket(pearSku);
        System.out.println("Before removing basket items - basket contains: " + basket.showBasket());
        basket.deleteItemsFromBasket();
        System.out.println("After deleting basket items - basket contains: " + basket.getBasketOfItems());

        Assert.assertEquals(0, basket.getBasketOfItems().size());
    }

}