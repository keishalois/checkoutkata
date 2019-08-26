package kata.Controller;

import kata.Model.Basket;
import kata.Model.Sku;
import kata.Repository.ItemRepo;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@RunWith(SpringRunner.class)
@WebMvcTest(BasketController.class)

public class BasketControllerTest {

    @MockBean
    ItemRepo itemRepo;
    @MockBean
    Basket basket;
    @Autowired
    private MockMvc mockMvc;


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
    public void addItemToBasketShouldPostItemToBasket() throws Exception {
        System.out.println("Test1");
        Sku appleSku = new Sku("Apple", 50);
        when(itemRepo.getSku("Apple")).thenReturn(appleSku);
        when(basket.addToBasket(appleSku)).thenReturn(appleSku);

        MvcResult mvcResult = this.mockMvc.perform(put("/basket/add/Apple")).andReturn();

        Assert.assertThat(mvcResult.getResponse().getStatus(), is(200));
        String jsonBasketString = "{\"Basket of items \": " + basket.getBasketOfItems() + "}";
        Assert.assertTrue(mvcResult.getResponse().getContentAsString().contains(jsonBasketString));
    }

    @Test
    public void seeBasketShouldReturnBasketOfItems() throws Exception {
        System.out.println("Test2");
        MvcResult mvcResult = this.mockMvc.perform(get("/basket")).andReturn();
        String jsonBasketString = "{\"Basket of items \": " + basket.getBasketOfItems() + "}";
        Assert.assertTrue(mvcResult.getResponse().getContentAsString().contains(jsonBasketString));
        Assert.assertThat(mvcResult.getResponse().getStatus(), is(200));
    }

    @Test
    public void addRandomItemThatDoesNotExistToBasketShouldReturnWrongRequest() throws Exception {
        System.out.println("Test3");
        MvcResult mvcResult = this.mockMvc.perform(put("/basket/add/Car")).andReturn();
        //400 is bad request error
        Assert.assertThat(mvcResult.getResponse().getStatus(),is(400));
    }

    @Test
    public void removeAllItemsFromBasketShouldReturnEmptyBasket() throws Exception {
        System.out.println("Test4");
        MvcResult mvcResult = this.mockMvc.perform(delete("/basket/remove")).andReturn();
        Assert.assertThat(mvcResult.getResponse().getStatus(), is(204));
    }


}

