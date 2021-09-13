package com.laonstory.oauth.autoconfigure.provider.jwt;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {
  private String secretKey = "default";
  private long validityMinMilliseconds = 431990000L;
  private long refreshValidityMinMilliseconds = 251840000000L;
}
