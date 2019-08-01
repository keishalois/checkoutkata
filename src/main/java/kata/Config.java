package kata;

import kata.Model.basket.Basket;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

//@Configuration
public class Config {

//    @Bean
//    public ItemRepo itemRepoA(){
//        return new ItemRepo();
//    }
//
    @Bean
    public kata.Model.basket.Basket basket(){
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
