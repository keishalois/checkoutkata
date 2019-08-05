package kata.View;

import kata.Model.basket.Basket;
import kata.Model.checkout.CheckoutService;

import javax.annotation.Resource;

@Resource
public class ReceiptResource {

    private CheckoutService checkout;
    private Basket basket;


    public ReceiptResource(CheckoutService checkout, Basket basket) {
        this.checkout = checkout;
        this.basket = basket;
    }


    private String showReceipt(){
return "hello";
    }
}
