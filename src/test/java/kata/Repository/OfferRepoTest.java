package kata.Repository;

import kata.Model.Offer;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;

import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)

public class OfferRepoTest {
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();
    @Mock
    OfferRepo offerRepo;

    @Test
    public void getExistingOfferShouldReturnOptionalOfferIfExists() {
        Offer appleOffer = new Offer(3,130);

        when(offerRepo.getOffer("Apple")).thenReturn(Optional.of(appleOffer));
        Optional<Offer> optionalOffer = offerRepo.getOffer("Apple");

        Assert.assertEquals(Optional.of(appleOffer), offerRepo.getOffer("Apple"));
        Assert.assertTrue(optionalOffer.isPresent());
    }

    @Test
    public void checkOfferDoesNotExistAndReturnEmptyOptional() {
        when(offerRepo.getOffer("Gin")).thenReturn(Optional.empty());
        Optional<Offer> optionalOffer = offerRepo.getOffer("Gin");

        Assert.assertFalse(optionalOffer.isPresent());
    }
}