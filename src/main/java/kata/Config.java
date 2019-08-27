package kata;

import kata.Model.Basket;
import kata.Repository.OfferRepo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public Basket basket(){
        return new Basket();
    }

    @Bean
    public OfferRepo offerRepo() { return new OfferRepo();}

}
