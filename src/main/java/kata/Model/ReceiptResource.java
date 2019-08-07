package kata.Model;

import kata.Model.Basket;
import kata.Service.CheckoutService;

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
