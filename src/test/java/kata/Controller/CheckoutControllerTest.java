package kata.Controller;

import kata.Model.Basket;
import kata.Model.Offer;
import kata.Model.Sku;
import kata.Repository.ItemRepo;
import kata.Repository.OfferRepo;
import kata.Service.CheckoutService;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.HashMap;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@WebMvcTest(BasketController.class)

public class CheckoutControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    Basket basket;

    @MockBean
    ItemRepo itemRepo;

    @InjectMocks
    CheckoutService checkoutService;

    @Mock
    OfferRepo offerRepo;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    public void checkoutServiceWorkingShouldReturnTotalPrice() throws Exception {
        Sku appleSku = new Sku("Apple", 50);
        Offer appleOffer = new Offer(3,130);
        basket.addToBasket(appleSku);
        HashMap<String, Offer> offersForOfferRepo = new HashMap<>();
        offersForOfferRepo.put("Apple", appleOffer);
        when(offerRepo.getOffer(appleSku.getNameOfProduct())).thenReturn(Optional.of(appleOffer));

        MvcResult mvcResult = this.mockMvc.perform(get("/checkout/pay")).andReturn();
        Assert.assertThat(mvcResult.getResponse().getStatus(), is(HttpStatus.OK.value()));

    }




}