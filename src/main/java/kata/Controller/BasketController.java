package kata.Controller;

import kata.basket.Basket;
import kata.repo.ItemRepo;
import kata.sku.Sku;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/basket")

public class BasketController {

    private Basket basket;
    @Lazy
    @Autowired
    public BasketController(Basket basket) {
        this.basket = basket;
    }

    @PostMapping("/add")
    public ResponseEntity addItemToBasket(@RequestParam("item") String skuName) {
        ItemRepo itemRepo = new ItemRepo();

        if (skuName != null && itemRepo.getAllSkus().containsKey(skuName)) {
            basket.addToBasket(itemRepo.getSku(skuName));
            return ResponseEntity.ok(basket);
        } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("SKU %s does not exist", skuName));
            }
    }




}
