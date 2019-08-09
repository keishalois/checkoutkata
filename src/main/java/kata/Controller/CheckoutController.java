package kata.Controller;

import kata.Model.Basket;
import kata.Service.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/checkout")
public class CheckoutController {
    private CheckoutService checkoutService;
    private Basket basket;

    @Autowired
    public CheckoutController(Basket basket, CheckoutService checkoutService){
        this.basket = basket;
        this.checkoutService = checkoutService;
    }

    @GetMapping("/pay")
    public ResponseEntity checkoutAndPay() {
        int checkout = checkoutService.checkoutBasket(basket);
        String jsonTotalString = "{\"Total to pay\": " + checkout + "}";
        return ResponseEntity.ok(jsonTotalString);
    }

}
