package kata.Controller;

import kata.Model.Basket;
import kata.Model.Sku;
import kata.Repository.ItemRepo;
import org.junit.Assert;
import org.junit.Test;
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


// ******* this test does not work yet *****

public class BasketControllerTest {

    @MockBean
    ItemRepo itemRepo;
    @MockBean
    Basket basket;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void addItemToBasketShouldPostItem() throws Exception {
        Sku appleSku = new Sku("Apple", 50);
        when(itemRepo.getSku("Apple")).thenReturn(appleSku);
        when(basket.addToBasket(appleSku)).thenReturn(appleSku);

//        ObjectMapper mapper = new ObjectMapper();
//        String jsonString = mapper.writeValueAsString(appleSku.getNameOfProduct());

//        MvcResult mvcResult = this.mockMvc.perform(put("/basket/add/Apple").contentType(MediaType.APPLICATION_JSON_UTF8).content(jsonString)).andReturn();
        MvcResult mvcResult = this.mockMvc.perform(put("/basket/add/Apple")).andReturn();

        Assert.assertThat(mvcResult.getResponse().getStatus(), is(200));
        verify(basket).addToBasket(appleSku);
    }

    @Test
    public void seeBasketShouldReturnBasket() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(get("/basket/see")).andReturn();
        Assert.assertThat(mvcResult.getResponse().getStatus(), is(200));
    }


}

