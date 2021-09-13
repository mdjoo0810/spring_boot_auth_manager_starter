package com.laonstory.oauth.autoconfigure.provider.jwt;

import com.laonstory.auth_provider.jwt.JwtTokenProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(JwtProperties.class)
@ConditionalOnClass(name = "com.laonstory.auth_provider.jwt.JwtTokenProvider")
public class JwtAutoConfiguration {

  @Bean
  @ConditionalOnMissingBean
  public JwtTokenProvider jwtTokenProvider(JwtProperties properties) {
    return new JwtTokenProvider(properties.getSecretKey(), properties.getValidityMinMilliseconds(),
        properties.getRefreshValidityMinMilliseconds());
  }

}
