package kata.Controller;

import kata.Model.Basket;
import kata.Repository.ItemRepo;
import kata.Repository.OfferRepo;
import kata.Service.CheckoutService;
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

@RunWith(SpringRunner.class)
@WebMvcTest(CheckoutController.class)

public class CheckoutControllerTest {
    @MockBean
    Basket basket;
    @MockBean
    CheckoutService checkoutService;
    @Autowired
    private MockMvc mockMvc;


    @Test
    public void checkoutServiceWorkingShouldReturnTotalPrice() throws Exception {
        when(checkoutService.checkoutBasket(basket)).thenReturn(50);

        MvcResult mvcResult = this.mockMvc.perform(get("/checkout/pay").contentType(MediaType.APPLICATION_JSON)).andReturn();

        Assert.assertThat(mvcResult.getResponse().getStatus(), is(HttpStatus.OK.value()));
        String jsonTotalString = "{\"Total to pay\": " + 50 + "}";
        Assert.assertTrue(mvcResult.getResponse().getContentAsString().contains(jsonTotalString));
    }


}