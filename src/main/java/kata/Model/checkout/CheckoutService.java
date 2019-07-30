package kata.Model.checkout;

import kata.Model.basket.Basket;
import kata.Model.offer.Offer;
import kata.Model.repo.OfferRepo;
import kata.Model.sku.Sku;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Set;

@Service
public class CheckoutService {

    private OfferRepo offerRepo;

    @Autowired
    public CheckoutService(OfferRepo offerRepo) {
        this.offerRepo = offerRepo;
        System.out.println("Creating kata.Model.checkout service with these offers " + offerRepo.getOffers().toString());
    }

    public int applyOffers(Offer offer, Sku sku, int basketQuantity) {
        int totalPrice = 0;

        int offerBundlePrice =  (basketQuantity / offer.getQuantityOfProduct()) * offer.getPriceInPence();
        int remainderItemsPrice = (basketQuantity % offer.getQuantityOfProduct()) * sku.getPrice();

        totalPrice += (offerBundlePrice + remainderItemsPrice);

        return totalPrice;
    }

    public int checkoutBasket(Basket basket) {
        int totalPrice = 0;
        Set<Sku> skus = basket.getBasketOfItems().keySet();
        HashMap<String, Offer> offers = offerRepo.getOffers();

        for (Sku sku : skus) {
            Offer offer = offers.get(sku.getNameOfProduct());
            if (offer != null) {
                totalPrice += applyOffers(offer, sku, basket.getQuantityInBasket(sku));
            } else {
                int quantity = basket.getBasketOfItems().get(sku);
                int cost = quantity * sku.getPrice();
                totalPrice += cost;
            }
        }

        return totalPrice;
    }

}
