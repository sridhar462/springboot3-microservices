package com.payil.courseservice.config;

import com.payil.courseservice.client.StudentClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.reactive.LoadBalancedExchangeFilterFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class WebClientConfig {

    @Autowired
    private LoadBalancedExchangeFilterFunction loadBalancedExchangeFilterFunction;

    @Bean
    public WebClient getWebClient(){
        return WebClient
                .builder()
                .baseUrl("http://student-service")
                .filter(loadBalancedExchangeFilterFunction)
                .build();
    }


    @Bean
    public StudentClient buildStudentClient(){
        return HttpServiceProxyFactory
                .builderFor(WebClientAdapter.create(getWebClient()))
                .build()
                .createClient(StudentClient.class);
    }
}
