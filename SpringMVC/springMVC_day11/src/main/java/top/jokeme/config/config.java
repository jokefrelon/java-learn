package top.jokeme.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import top.jokeme.service.serviceImpl.UserServiceImpl;

@Import(annoAspectjConfig.class)
@Configuration
public class config {

    @Bean
    public UserServiceImpl getNewUserServiceImpl(){
     return new UserServiceImpl();
    }
}
