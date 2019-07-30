package kata.Controller;

import kata.Model.basket.Basket;
import kata.Model.checkout.CheckoutService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping("/checkout")
public class CheckoutController {
    private CheckoutService checkoutService;
    private Basket basket;

    public CheckoutController(Basket basket, CheckoutService checkoutService){
        this.basket = basket;
        this.checkoutService = checkoutService;
    }

    @GetMapping("/pay")
    public int checkoutAndPay() {
        int checkout =checkoutService.checkoutBasket(basket);
        return checkout;
    }

}
