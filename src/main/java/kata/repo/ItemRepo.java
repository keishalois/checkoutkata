package kata.repo;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import kata.sku.Sku;

import java.util.HashMap;


@Component
public class ItemRepo {

    HashMap<String, Sku> skus = new HashMap<>();

    public ItemRepo() {
        skus.put("Apple", new Sku("Apple", 50));
        skus.put("Pear", new Sku("Pear", 30));
        skus.put("Cider", new Sku("Cider", 500));
        skus.put("Gin", new Sku("Gin", 1000));
    }

    public HashMap<String, Sku> getAllSkus() {
        return skus;
    }

    public Sku getSku(String name) {
       return skus.get(name);
    }
}
