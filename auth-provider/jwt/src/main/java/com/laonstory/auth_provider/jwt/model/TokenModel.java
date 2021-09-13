package com.laonstory.auth_provider.jwt.model;

import lombok.Builder;
import lombok.Getter;

@Getter
public class TokenModel {

  private final String tokenType;
  private final String accessToken;
  private final Integer expiresIn;
  private final String refreshToken;
  private final Integer refreshExpiresIn;

  @Builder
  public TokenModel(String tokenType, String accessToken, Integer expiresIn,
      String refreshToken, Integer refreshExpiresIn) {
    this.tokenType = tokenType;
    this.accessToken = accessToken;
    this.expiresIn = expiresIn;
    this.refreshToken = refreshToken;
    this.refreshExpiresIn = refreshExpiresIn;
  }

  @Override
  public String toString() {
    return "TokenModel{" +
        "tokenType='" + tokenType + '\'' +
        ", accessToken='" + accessToken + '\'' +
        ", expiresIn=" + expiresIn +
        ", refreshToken='" + refreshToken + '\'' +
        ", refreshExpiresIn=" + refreshExpiresIn +
        '}';
  }
}