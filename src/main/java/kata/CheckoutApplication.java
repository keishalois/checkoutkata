package kata;

import kata.checkout.CheckoutService;
import kata.offer.Offer;
import kata.repo.ItemRepo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import kata.repo.OfferRepo;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CheckoutApplication {

    public static void main(String[] args) {
        SpringApplication.run(CheckoutApplication.class, args);
    }

}
