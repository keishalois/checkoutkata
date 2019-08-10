package kata;

import kata.Model.Basket;
import kata.Repository.ItemRepo;
import kata.Repository.OfferRepo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean
    public ItemRepo itemRepoA(){
        return new ItemRepo();
    }

    @Bean
    public Basket basket(){
        return new Basket();
    }

    @Bean
    public OfferRepo offerRepo() { return new OfferRepo();}

//    private String username;
//
//    public Config(@Value("${username}") String username){
//        this.username = username;
//    }
//
//    public String getUsername() {
//        return username;
//    }
}
