package kata.Controller;

import kata.Model.Basket;
import kata.Model.Sku;
import kata.Repository.ItemRepo;
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

    @PutMapping("/add/{skuName}")
    public ResponseEntity addItemToBasket(@PathVariable String skuName) {
        Sku sku = itemRepo.getSku(skuName);
        if (sku != null) {
            basket.addToBasket(sku);
            String jsonBasketString = "{\"Basket of items \": " + basket.getBasketOfItems() + "}";
            return ResponseEntity.ok(jsonBasketString);
        } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(String.format("Not able to add SKU %s to basket", skuName));
            }
    }

    @GetMapping()
    public ResponseEntity seeBasket(){
        String jsonBasketString = "{\"Basket of items \": " + basket.getBasketOfItems() + "}";
        return ResponseEntity.ok(jsonBasketString);
    }

    @DeleteMapping("/remove")
    public ResponseEntity deleteBasket(){
        System.out.println(basket.getBasketOfItems());
        basket.deleteItemsFromBasket();
        String jsonBasketString = "{\"Basket of items is no more \": " + basket.getBasketOfItems() + "}";
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(String.format("Removed basket"));
    }

}
