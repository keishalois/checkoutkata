package kata.Repository;

import kata.Model.Offer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Optional;

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

    public Optional<Offer> getOffer(String name) {
        Optional<Offer> optionalOffer = Optional.empty();

        if (getOffers().containsKey(name)){
            return Optional.of(offers.get(name));
        } else {
        return optionalOffer;
        }
    }

}

