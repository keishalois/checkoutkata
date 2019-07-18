package checkout;

import basket.Basket;
import sku.Sku;

import java.util.Set;

public class Checkout {
    private int totalCost;
    private int totalItems;

    public Checkout() {

    }

    public int basketCost(Basket basket) {
        int totalprice = 0;
        Set<Sku> skus = basket.getBasketOfItems().keySet();
        for (Sku sku : skus) {
            int quantity = basket.getBasketOfItems().get(sku);
            int cost = quantity * sku.getPrice();
            totalprice += cost;
        }
        return totalprice;
    }

    // get individual sku quantity and compare against offer price

}
