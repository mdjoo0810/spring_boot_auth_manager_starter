package com.laonstory.auth_provider.jwt;


import com.laonstory.auth_provider.jwt.model.GenerateTokenOption;
import com.laonstory.auth_provider.jwt.model.TokenModel;
import com.laonstory.oauth.core.util.DateUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Base64;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;

@Slf4j
public class JwtTokenProvider implements InitializingBean {
  private final String secretKey;
  private final long validityMinMilliseconds;
  private final long refreshValidityMinMilliseconds;

  public JwtTokenProvider(String secretKey, long validityMinMilliseconds,
      long refreshValidityMinMilliseconds) {
    this.secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    this.validityMinMilliseconds = validityMinMilliseconds;
    this.refreshValidityMinMilliseconds = refreshValidityMinMilliseconds;
  }

  // 토큰 발행
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

  // 유니크 파라미터 추출
  public String getUniqueParam(String token) {
    return Jwts.parser()
        .setSigningKey(secretKey)
        .parseClaimsJws(token)
        .getBody()
        .getSubject();
  }

  // Http Header 에서 토큰 추출
  public String resolveToken(HttpServletRequest request) {
    return request.getHeader("Authorization");
  }

  public boolean validateToken(String token) {
    try {
      Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
      return !claimsJws.getBody().getExpiration().before(new Date());
    } catch (Exception e) {
      log.info("validate token failed : {}", e.getMessage());
      return false;
    }
  }

  private String createToken(Claims claims, Date now, Date expiration) {
    return Jwts.builder()
        .setClaims(claims)
        .setIssuedAt(now)
        .setExpiration(expiration)
        .signWith(SignatureAlgorithm.HS256, secretKey)
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
