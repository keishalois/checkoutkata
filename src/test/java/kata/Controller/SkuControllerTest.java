package kata.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import kata.Repository.ItemRepo;
import kata.Model.Sku;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@WebMvcTest(SkuController.class)

public class SkuControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ItemRepo itemRepo;

    @Test
    public void getSkuItemBasedOnStringShouldReturnSku() throws Exception {
        Sku appleSku = new Sku("Apple", 50);
        when(itemRepo.getSku(appleSku.getNameOfProduct())).thenReturn(appleSku);

        MvcResult mvcResult = this.mockMvc.perform(get("/skus/Apple")).andReturn();

        Assert.assertThat(mvcResult.getResponse().getStatus(), is(HttpStatus.OK.value()));
        Assert.assertTrue(mvcResult.getResponse().getContentAsString().contains("\"nameOfProduct\":\"Apple\""));
        Assert.assertTrue(mvcResult.getResponse().getContentAsString().contains("\"price\":50"));
    }


    @Test
    public void postSkuItemBasedOnStringShouldReturnSku() throws Exception {
        Sku appleSku = new Sku("Apple", 50);
        when(itemRepo.getSku(appleSku.getNameOfProduct())).thenReturn(null);
        when(itemRepo.addSku(appleSku)).thenReturn(appleSku);

        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(appleSku);
        MvcResult mvcResult = this.mockMvc.perform(post("/skus").contentType(MediaType.APPLICATION_JSON).content(jsonString)).andReturn();

        Assert.assertThat(mvcResult.getResponse().getStatus(), is(201));
        Assert.assertTrue(mvcResult.getResponse().getContentAsString().contains("\"nameOfProduct\":\"Apple\""));
        Assert.assertTrue(mvcResult.getResponse().getContentAsString().contains("\"price\":50"));
    }


}