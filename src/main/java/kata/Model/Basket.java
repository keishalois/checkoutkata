package kata.Model;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Set;

@Component
public class Basket {

    private HashMap <Sku, Integer> basketOfItems;

    public Basket() {
    this.basketOfItems = new HashMap <Sku, Integer>();
    }

    public HashMap<Sku, Integer> getBasketOfItems() {
        return basketOfItems;
    }

    public Sku addToBasket(Sku sku) {
        if (basketOfItems.containsKey(sku)) {
            basketOfItems.put(sku, basketOfItems.get(sku) + 1);
        } else {
            basketOfItems.put(sku, 1);
        }
        return sku;
    }

    public int getQuantityInBasket(Sku sku) {
        if (basketOfItems.containsKey(sku)) {
            return basketOfItems.get(sku);
        }
        return 0;
    }

    public Set<Sku> showBasket(){
        Set<Sku> skus = getBasketOfItems().keySet();
        for (Sku sku : skus) {
            System.out.println("Item : " + sku.getNameOfProduct());
            System.out.println("Normal Price : "  + sku.getPrice());
            System.out.println("Quantity in basket : " + getQuantityInBasket(sku));
        }
        return skus;
    }

}
