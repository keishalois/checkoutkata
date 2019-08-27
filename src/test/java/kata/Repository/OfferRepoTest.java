package kata.Repository;

import kata.Model.Offer;
import org.junit.*;
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
    @Before
    public void before() {
        System.out.println("Before");
    }
    @After
    public void after() {
        System.out.println("After");
    }
    @BeforeClass
    public static void beforeClass() {
        System.out.println("Before Class");
    }
    @AfterClass
    public static void afterClass() {
        System.out.println("After Class");
    }

    @Test
    public void getExistingOfferShouldReturnOptionalOfferIfExists() {
        System.out.println("Test1");

        Offer appleOffer = new Offer(3,130);

        when(offerRepo.getOffer("Apple")).thenReturn(Optional.of(appleOffer));
        Optional<Offer> optionalOffer = offerRepo.getOffer("Apple");

        Assert.assertEquals(Optional.of(appleOffer), offerRepo.getOffer("Apple"));
        Assert.assertTrue(optionalOffer.isPresent());
    }

    @Test
    public void checkOfferDoesNotExistAndReturnEmptyOptional() {
        System.out.println("Test2");

        when(offerRepo.getOffer("Gin")).thenReturn(Optional.empty());
        Optional<Offer> optionalOffer = offerRepo.getOffer("Gin");

        Assert.assertFalse(optionalOffer.isPresent());
    }
}