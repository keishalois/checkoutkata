package kata.basket;

import kata.sku.Sku;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Set;

@Lazy
@Component
public class Basket {

    private HashMap <Sku, Integer> basketOfItems;

    public Basket() {
    this.basketOfItems = new HashMap <Sku, Integer>();
    }

    public HashMap<Sku, Integer> getBasketOfItems() {
        return basketOfItems;
    }

    public void addToBasket(Sku sku) {
        if (basketOfItems.containsKey(sku)) {
            basketOfItems.put(sku, basketOfItems.get(sku) + 1);
        } else {
            basketOfItems.put(sku, 1);
        }
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
