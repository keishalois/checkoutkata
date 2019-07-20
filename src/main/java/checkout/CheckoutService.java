package checkout;

import basket.Basket;
import offer.Offer;
import repo.OfferRepo;
import sku.Sku;

import java.util.HashMap;
import java.util.Set;

public class CheckoutService {

    public CheckoutService() {
    }

    public int getSkuOfferMultiples(OfferRepo offerRepo, Sku sku, Basket basket) {
        HashMap<String, Offer> offers = offerRepo.getOffers();
        Offer skuOffer = offers.get(sku.getNameOfProduct());

        if (offers.containsKey(sku.getNameOfProduct())) {
            int numberOfOfferBundles = basket.getQuantityInBasket(sku) / skuOffer.getQuantityOfProduct();
            return numberOfOfferBundles;
        } else {
            return 0;
        }
    }

    public int getSkuOfferRemainders(OfferRepo offerRepo, Sku sku, Basket basket) {
        HashMap<String, Offer> offers = offerRepo.getOffers();
        Offer skuOffer = offers.get(sku.getNameOfProduct());

        if (offers.containsKey(sku.getNameOfProduct())) {
            int remainder = basket.getQuantityInBasket(sku) % skuOffer.getQuantityOfProduct();
            return remainder;
        } else {
            return 0;
        }
    }

    public int applyOffers(OfferRepo offerRepo, Sku sku, Basket basket) {
        int totalPrice = 0;

        HashMap<String, Offer> offers = offerRepo.getOffers();
        Offer skuOffer = offers.get(sku.getNameOfProduct());

        int offerBundlePrice = (offers.containsKey(sku.getNameOfProduct())) ? getSkuOfferMultiples(offerRepo, sku, basket) * skuOffer.getPriceInPence() : 0;
        int remainderItemsPrice = (offers.containsKey(sku.getNameOfProduct()))  ? getSkuOfferRemainders(offerRepo, sku, basket) * sku.getPrice() : 0;

        totalPrice += (offerBundlePrice + remainderItemsPrice);

        return totalPrice;
    }

    public int checkoutBasket(OfferRepo offerRepo, Basket basket) {
        int totalPrice = 0;
        Set<Sku> skus = basket.getBasketOfItems().keySet();
        HashMap<String, Offer> offers = offerRepo.getOffers();

        for (Sku sku : skus) {
            if (offers.containsKey(sku.getNameOfProduct())) {
                totalPrice += applyOffers(offerRepo, sku, basket);
            } else {
                int quantity = basket.getBasketOfItems().get(sku);
                int cost = quantity * sku.getPrice();
                totalPrice += cost;
            }
        }

        return totalPrice;
    }

}
