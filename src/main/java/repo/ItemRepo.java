package repo;

import sku.Sku;
import java.util.HashSet;
import java.util.Set;

public class ItemRepo {

    Set<Sku> skus = new HashSet<Sku>();

    public ItemRepo() {
        skus.add(new Sku("Apple", 50));
        skus.add(new Sku("Pear", 30));
        skus.add(new Sku("Cider", 500));
        skus.add(new Sku("Gin", 1000));
    }

    public Set<Sku> getSkus() {
        return skus;
    }

}