package kata.checkout;

import kata.Config;
import kata.basket.Basket;
import kata.offer.Offer;
import kata.repo.ItemRepo;
import kata.repo.OfferRepo;
import kata.sku.Sku;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Set;

@Service
public class CheckoutService {


    private OfferRepo offerRepo;

    @Value("${username}")
    private String username;


    @Autowired
    public CheckoutService(OfferRepo offerRepo) {

        this.offerRepo = offerRepo;
        System.out.println("Creating kata.checkout service with these offers " + offerRepo.getOffers().toString());
        System.out.println(username);
    }


    public int applyOffers(Offer offer, Sku sku, int basketquantity) {
        int totalPrice = 0;

        int offerBundlePrice =  (basketquantity / offer.getQuantityOfProduct()) * offer.getPriceInPence();
        int remainderItemsPrice = (basketquantity % offer.getQuantityOfProduct()) * sku.getPrice();

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
