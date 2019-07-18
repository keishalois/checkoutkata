package repo;

import basket.Basket;
import sku.Sku;
import offer.Offer;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class OfferRepo {

    HashMap<String, Offer> offers = new HashMap<String, Offer>();

    public OfferRepo() {
        offers.put("Apple", new Offer(3, 130));
        offers.put("Pear", new Offer(2, 45));
    }

    public HashMap<String, Offer> getOffers() {
        return offers;
    }


    public int getSkuOffer(ItemRepo itemRepo, Sku skuItem, Basket basket) {
        //Arrange
        HashMap<String, Offer> offers = getOffers();
        Offer skuOffer = offers.get(skuItem.getNameOfProduct());
        String itemRepoSearch = itemRepo.getParticularSkuName(skuItem);
        //Act
        if ((offers.containsKey(itemRepoSearch)) && (basket.getQuantityInBasket(skuItem) == skuOffer.getQuantityOfProduct())) {
            System.out.println("Original price of " + skuItem.getNameOfProduct() + " : " + skuItem.getPrice() + "p");
            skuItem.setPrice(skuOffer.getPriceInPence());
            System.out.println("There are " + basket.getQuantityInBasket(skuItem) + " in your basket ");
            System.out.println("On offer:  " + skuItem.getNameOfProduct() + " now worth " + skuItem.getPrice() + "p since the offer price is " + skuOffer.getPriceInPence() + "p when basket quantity is " + skuOffer.getQuantityOfProduct() + " ");
            return skuItem.getPrice();
            }
        System.out.println("There are " + basket.getQuantityInBasket(skuItem) + " in your basket");
        System.out.println("Sorry not a deal, this will cost " + skuItem.getPrice() + "p");
        return skuItem.getPrice();
    }

    public int getSkuOfferMultiples(ItemRepo itemRepo, Sku skuItem, Basket basket) {
        //Count number of multiples for deal var count -  basket.getquantityinbasket(sku) % skuoffer.getquantityofproduct === 0

        //Arrange
        HashMap<String, Offer> offers = getOffers();
        Offer skuOffer = offers.get(skuItem.getNameOfProduct());
        String itemRepoSearch = itemRepo.getParticularSkuName(skuItem);
        //work on this - needs to check on number of offer bundles and insert getskuofferremainders
        //do i really need this if statement? could just remove it and have the checkout use both getoffermultiples and getofferremainders
        if (offers.containsKey(itemRepoSearch) && (getSkuOfferRemainders(skuItem,basket)==0)) {
            int numberOfOfferBundles = basket.getQuantityInBasket(skuItem)/ skuOffer.getQuantityOfProduct();
            System.out.println(numberOfOfferBundles);
            return numberOfOfferBundles;
        }
        return 0;
        //multiply count by offer price in checkout
    }

    public int getSkuOfferRemainders(Sku skuItem, Basket basket) {
        //Arrange
        HashMap<String, Offer> offers = getOffers();
        Offer skuOffer = offers.get(skuItem.getNameOfProduct());
        // catch in variable remainder and multiply by sku price at checkout
    int remainder = basket.getQuantityInBasket(skuItem)%skuOffer.getQuantityOfProduct();
    return remainder;
    }

    }

