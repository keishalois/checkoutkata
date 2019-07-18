package basket;

import sku.Sku;

import java.util.HashMap;

public class Basket {

    private HashMap <Sku, Integer> basketOfItems;

    public Basket() {
    this.basketOfItems = new HashMap <Sku, Integer>();
    }

    public HashMap<Sku, Integer> getBasketOfItems() {
        return basketOfItems;
    }

//add sku to basket
    public void addToBasket(Sku sku) {
        if (basketOfItems.containsKey(sku)) {
            basketOfItems.put(sku, basketOfItems.get(sku) + 1);
        } else {basketOfItems.put(sku, 1); }
    }

    public int getQuantityInBasket(Sku sku) {
        if (basketOfItems.containsKey(sku)) {
            return basketOfItems.get(sku);
        }
        return 0;
    }

    //loop through all items in basket
    // get number of items in basket
    // cancel basket
    //remove sku from basket
}
