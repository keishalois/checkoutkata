package kata.Controller;

import kata.Model.Basket;
import kata.Repository.ItemRepo;
import kata.Repository.OfferRepo;
import kata.Service.CheckoutService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@WebMvcTest(CheckoutController.class)


// ******* this test does not work yet *****
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

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void checkoutServiceWorkingShouldReturnTotalPrice() throws Exception {

//        Sku appleSku = new Sku("Apple", 50);
//        when(basket.addToBasket(appleSku)).thenReturn(appleSku);
//        basket.addToBasket(appleSku);
//
//        HashMap<Sku, Integer> newBasket = new HashMap<>();
//        newBasket.put(appleSku,1);
//        when(basket.getBasketOfItems()).thenReturn();
//
//        Offer appleOffer = new Offer(3,130);
//        when(offerRepo.getOffer(appleSku.getNameOfProduct())).thenReturn(Optional.of(appleOffer));

        when(checkoutService.checkoutBasket(basket)).thenReturn(50);

        MvcResult mvcResult = this.mockMvc.perform(get("/checkout/pay")).andReturn();

        Assert.assertThat(mvcResult.getResponse().getStatus(), is(HttpStatus.OK.value()));
        String jsonTotalString = "{\"total\":" + 50 + "}";
        Assert.assertTrue(mvcResult.getResponse().getContentAsString().contains(jsonTotalString));
//        verify(basket).addToBasket(appleSku);
        verify(basket).getBasketOfItems();
//        verify(offerRepo).getOffer(appleSku.getNameOfProduct());

    }


}