package kata.Controller;

import kata.Model.basket.Basket;
import kata.Model.repo.ItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/add")
    public ResponseEntity addItemToBasket(@RequestParam("item") String skuName) {

        if (skuName != null && itemRepo.getAllSkus().containsKey(skuName)) {
            basket.addToBasket(itemRepo.getSku(skuName));
            return ResponseEntity.ok(basket);
        } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("Not able to add SKU %s to basket", skuName));
            }
    }

    @GetMapping("/see")
    public ResponseEntity seeBasket(){
        return ResponseEntity.ok(basket);
    }

}
