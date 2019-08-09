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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;


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
        String jsonBasketString = "{\"Basket of items \": " + basket + "}";
        Assert.assertTrue(mvcResult.getResponse().getContentAsString().contains(jsonBasketString));
    }

    @Test
    public void seeBasketShouldReturnBasketOfItems() throws Exception {
        System.out.println("Test2");
        MvcResult mvcResult = this.mockMvc.perform(get("/basket/see")).andReturn();
        String jsonBasketString = "{\"Basket of items \": " + basket + "}";
        Assert.assertTrue(mvcResult.getResponse().getContentAsString().contains(jsonBasketString));
        Assert.assertThat(mvcResult.getResponse().getStatus(), is(200));
    }


}

