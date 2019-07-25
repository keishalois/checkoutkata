package kata.repo;

import kata.offer.Offer;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
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

