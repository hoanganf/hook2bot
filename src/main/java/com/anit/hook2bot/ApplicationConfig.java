package com.anit.hook2bot;

import com.anit.domain.service.ChatMessegeBuilder;
import com.anit.domain.service.WebhookService;
import com.anit.hook2bot.infrastructure.client.LoggingInterceptor;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author anit
 */
@Slf4j
@Configuration
public class ApplicationConfig {
    @Bean
    public WebhookService webhookService() {
        return new WebhookService(new ChatMessegeBuilder());
    }

    @Bean
    public RestTemplate googleChatRestTemplate() {
        return this.createRestTemplate();
    }

    private RestTemplate createRestTemplate() {
        var simpleClientHttpRequestFactory = new SimpleClientHttpRequestFactory();
        simpleClientHttpRequestFactory.setConnectTimeout(3000);
        simpleClientHttpRequestFactory.setReadTimeout(3000);
        RestTemplate restTemplate;
        if (log.isDebugEnabled()) {
            var factory = new BufferingClientHttpRequestFactory(simpleClientHttpRequestFactory);
            restTemplate = new RestTemplate(factory);
            List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();
            if (CollectionUtils.isEmpty(interceptors)) {
                interceptors = new ArrayList<>();
            }
            interceptors.add(new LoggingInterceptor());
            restTemplate.setInterceptors(interceptors);

        } else {
            restTemplate = new RestTemplate(simpleClientHttpRequestFactory);
        }
        restTemplate.setMessageConverters(Collections.singletonList(new MappingJackson2HttpMessageConverter()));
        return restTemplate;
    }

}
