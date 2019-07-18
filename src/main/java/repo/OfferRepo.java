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
    }

