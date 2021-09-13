package com.laonstory.auth_provider.jwt;


import com.laonstory.auth_provider.jwt.model.GenerateTokenOption;
import com.laonstory.auth_provider.jwt.model.TokenModel;
import com.laonstory.oauth.core.util.DateUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Base64;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;

@Slf4j
public class JwtTokenProvider implements InitializingBean {
  private final String secretKey;
  private final long validityMinMilliseconds;
  private final long refreshValidityMinMilliseconds;

  public JwtTokenProvider(String secretKey, long validityMinMilliseconds,
      long refreshValidityMinMilliseconds) {
    this.secretKey = secretKey;
    this.validityMinMilliseconds = validityMinMilliseconds;
    this.refreshValidityMinMilliseconds = refreshValidityMinMilliseconds;
  }

  public TokenModel getTokens(String uniqueParam) {
    GenerateTokenOption options = getGenerateTokenOptions(uniqueParam);
    TokenModel tokenModel = TokenModel.builder()
        .tokenType("Bearer")
        .accessToken(createToken(options.getClaims(), options.getNow(),
            options.getValidityMinMilliseconds()))
        .expiresIn(DateUtils.convertMillisecondToSecond(validityMinMilliseconds))
        .refreshToken(createToken(options.getClaims(), options.getNow(),
            options.getRefreshValidityMinMilliseconds()))
        .refreshExpiresIn(DateUtils.convertMillisecondToSecond(refreshValidityMinMilliseconds))
        .build();

    log.info("Generate token successfully : {}", tokenModel.toString());

    return tokenModel;
  }

  private String createToken(Claims claims, Date now, Date expiration) {
    return Jwts.builder()
        .setClaims(claims)
        .setIssuedAt(now)
        .setExpiration(expiration)
        .signWith(SignatureAlgorithm.HS256, Base64.getEncoder().encodeToString(secretKey.getBytes()))
        .compact();
  }

  private GenerateTokenOption getGenerateTokenOptions(String uniqueParam) {
    Date now = new Date();

    return GenerateTokenOption.builder()
        .claims(Jwts.claims().setSubject(uniqueParam))
        .now(now)
        .validityMinMilliseconds(new Date(now.getTime() + validityMinMilliseconds))
        .refreshValidityMinMilliseconds(new Date(now.getTime() + refreshValidityMinMilliseconds))
        .build();
  }

  @Override
  public void afterPropertiesSet() throws Exception {
    log.info("[PROVIDER] Registered JwtTokenProvider.");
  }
}
