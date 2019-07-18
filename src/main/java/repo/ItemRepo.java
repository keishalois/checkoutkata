package repo;

import sku.Sku;
import java.util.HashSet;
import java.util.Set;

public class ItemRepo {

    Set<Sku> skus = new HashSet<Sku>();

    public ItemRepo(){
        skus.add(new Sku("Apple", 120));
        skus.add(new Sku("Pear", 100));
        skus.add(new Sku("Cider", 500));
        skus.add(new Sku("Gin", 1000));
    }

    public Set<Sku> getSkus(){
        return skus;
    }

    public Set<Sku> getAllSkuItemsAsSet(){
        Set<Sku> skus = getSkus();
        for (Sku sku : skus) {
            System.out.println("Value: " + sku.getNameOfProduct() + " ");
    }
        return skus;
    }

    public String getParticularSkuName(Sku skuitem){
        Set<Sku> skus = getSkus();
        for (Sku sku : skus) {
            if (sku.equals(skuitem)) {
                System.out.println("Matches: " + sku.getNameOfProduct() + " ");
                return sku.getNameOfProduct();
            }
        }
        System.out.println("No match");
        return "No match";
    }

    public int getParticularSkuPrice(Sku skuitem) {
        Set<Sku> skus = getSkus();
        for (Sku sku : skus) {
            if (sku.equals(skuitem)) {
                System.out.println("Price of " + sku.getNameOfProduct() + " is " + sku.getPrice() + " ");
                return sku.getPrice();
            }
        }
        System.out.println("That item technically is not in our inventory so have it for free");
        return 0;
    }

    }

