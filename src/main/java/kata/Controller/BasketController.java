package kata.Controller;

import com.fasterxml.jackson.databind.node.TextNode;
import kata.Model.Basket;
import kata.Repository.ItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/basket")

public class BasketController {

    private ItemRepo itemRepo;
    private Basket basket;

    @Autowired
    public BasketController(ItemRepo itemRepo, Basket basket) {
        this.itemRepo = itemRepo;
        this.basket = basket;
    }

    @PostMapping()
    public ResponseEntity addItemToBasket(@RequestBody String skuName) throws URISyntaxException {

        if (skuName != null && itemRepo.getAllSkus().containsKey(skuName)) {
            basket.addToBasket(itemRepo.getSku(skuName));
            return ResponseEntity.created(new URI("basket/see")).body(basket);
        } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("Not able to add SKU %s to basket", skuName));
            }
    }

    @GetMapping("/see")
    public ResponseEntity seeBasket(){
        return ResponseEntity.ok(basket);
    }

}
