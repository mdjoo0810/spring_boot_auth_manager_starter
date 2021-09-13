package com.laonstory.oauth.autoconfigure;

import static com.laonstory.oauth.core.util.RestTemplateFactory.getRestOperations;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestOperations;

/**
 * Created by Aaron on 2021/09/06
 */
@Configuration
@ConditionalOnProperty(prefix = "auth", name = "enable", havingValue = "true", matchIfMissing = true)
public class AuthAutoConfiguration {

  @Bean
  @Primary
  public RestOperations authRestOperations() {
    HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
    factory.setConnectTimeout(1000);
    factory.setReadTimeout(1000);
    return getRestOperations(factory);
  }

}
