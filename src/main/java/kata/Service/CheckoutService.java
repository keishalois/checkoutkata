package kata.Service;

import kata.Model.Basket;
import kata.Model.Offer;
import kata.Repository.OfferRepo;
import kata.Model.Sku;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class CheckoutService {

    private OfferRepo offerRepo;

    @Autowired
    public CheckoutService(OfferRepo offerRepo) {
        this.offerRepo = offerRepo;
        System.out.println("Creating kata.Model.checkout service with these offers " + offerRepo.getOffers().toString());
    }

    private int applyOffers(Offer offer, Sku sku, int basketQuantity) {
        int totalPrice = 0;

        int offerBundlePrice =  (basketQuantity / offer.getQuantityOfProduct()) * offer.getPriceInPence();
        int remainderItemsPrice = (basketQuantity % offer.getQuantityOfProduct()) * sku.getPrice();

        totalPrice += (offerBundlePrice + remainderItemsPrice);

        return totalPrice;
    }

    public int checkoutBasket(Basket basket) {
        int totalPrice = 0;
        Set<Sku> skus = basket.getBasketOfItems().keySet();
        for (Sku sku : skus) {
            Optional<Offer> optOffer = offerRepo.getOffer(sku.getNameOfProduct());
            if (optOffer.isPresent()) {
                totalPrice += applyOffers(optOffer.get(), sku, basket.getQuantityInBasket(sku));
            } else {
                int quantity = basket.getBasketOfItems().get(sku);
                int cost = quantity * sku.getPrice();
                totalPrice += cost;
            }
        }

        return totalPrice;
    }

}
