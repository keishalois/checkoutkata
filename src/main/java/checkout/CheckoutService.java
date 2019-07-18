package checkout;

import basket.Basket;
import offer.Offer;
import repo.ItemRepo;
import repo.OfferRepo;
import sku.Sku;

import java.util.HashMap;
import java.util.Set;

public class CheckoutService {
    private int totalCost;
    private int totalItems;

    public CheckoutService() {
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

//    public int getSkuOffer(OfferRepo offerRepo, Sku skuItem, Basket basket) {
//        HashMap<String, Offer> offers = offerRepo.getOffers();
//        Offer skuOffer = offers.get(skuItem.getNameOfProduct());
//
//        if ((offers.containsKey(skuItem.getNameOfProduct())) && (basket.getQuantityInBasket(skuItem) == skuOffer.getQuantityOfProduct())) {
//            System.out.println("Original price of " + skuItem.getNameOfProduct() + " : " + skuItem.getPrice() + "p");
//            skuItem.setPrice(skuOffer.getPriceInPence());
//            System.out.println("There are " + basket.getQuantityInBasket(skuItem) + " in your basket ");
//            System.out.println("On offer:  " + skuItem.getNameOfProduct() + " now worth " + skuItem.getPrice() + "p since the offer price is " + skuOffer.getPriceInPence() + "p when basket quantity is " + skuOffer.getQuantityOfProduct() + " ");
//            return skuItem.getPrice();
//        }
//        System.out.println("There are " + basket.getQuantityInBasket(skuItem) + " in your basket");
//        System.out.println("Sorry not a deal, this will cost " + skuItem.getPrice() + "p");
//        return skuItem.getPrice();
//    }

    public int getSkuOfferMultiples(OfferRepo offerRepo, Sku skuItem, Basket basket) {
        //Count number of multiples for deal var count -  basket.getquantityinbasket(sku) % skuoffer.getquantityofproduct === 0
        HashMap<String, Offer> offers = offerRepo.getOffers();
        Offer skuOffer = offers.get(skuItem.getNameOfProduct());
        if (offers.containsKey(skuItem.getNameOfProduct())) {
            int numberOfOfferBundles = basket.getQuantityInBasket(skuItem) / skuOffer.getQuantityOfProduct();
            System.out.println("deal bundle applied: " + numberOfOfferBundles);
            return numberOfOfferBundles;
        }
       return 0;
        //multiply count by offer price in checkout
    }

    public int getSkuOfferRemainders(OfferRepo offerRepo, Sku skuItem, Basket basket) {
        HashMap<String, Offer> offers = offerRepo.getOffers();
        Offer skuOffer = offers.get(skuItem.getNameOfProduct());
        // catch in variable remainder and multiply by sku price at checkout
        int remainder = basket.getQuantityInBasket(skuItem)%skuOffer.getQuantityOfProduct();
        return remainder;
    }

    // get individual sku quantity and compare against offer price

}
