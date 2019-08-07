package kata.Controller;

import kata.Model.Basket;
import kata.Repository.ItemRepo;
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc

public class BasketControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Mock
    ItemRepo itemRepo;

    @Mock
    Basket basket;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

//    @Test
//    public void addItemToBasketShouldPostItem() throws Exception {
//            this.mockMvc.perform(get("localhost:8080/basket/add")).andExpect(status().isOk())
//                    .andExpect();
//        }
//    }

}