package top.jokeme.config;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Bean;

@Aspect
public class annoAspectjConfig  {


    @Bean
    @Before("execution( *  top.jokeme.service.serviceImpl.UserServiceImpl.* (..))")
    public void before()  {
        System.out.println("----- exec before -----");
    }

    @Bean
    @After("execution( *  top.jokeme.service.serviceImpl.UserServiceImpl.* (..))")
    public void after(){
        System.out.println("----- exec after -----");
    }


}
