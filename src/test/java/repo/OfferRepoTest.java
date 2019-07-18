package repo;

import basket.Basket;
import offer.Offer;
import org.junit.Assert;
import org.junit.Test;
import sku.Sku;

import static org.junit.Assert.*;

public class OfferRepoTest {

    @Test
    public void getOffersReturnsOffers() {
        OfferRepo repo = new OfferRepo();
        System.out.println(repo.getOffers());
    }
}