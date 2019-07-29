package kata;

import kata.basket.Basket;
import kata.repo.ItemRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class Config {

//    @Bean
//    public ItemRepo itemRepoA(){
//        return new ItemRepo();
//    }
//
    @Bean
    public kata.basket.Basket basket(){
        return new Basket();
    }

    private String username;

    public Config(@Value("${username}") String username){
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
