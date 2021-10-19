package top.jokeme.config;

import org.springframework.context.annotation.Bean;

import top.jokeme.pojo.User;

public class config {

    @Bean
    public User Ruser(){
        return new User();
    }
}
