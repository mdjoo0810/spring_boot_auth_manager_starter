package com.laonstory.oauth.core.util;

import static org.junit.jupiter.api.Assertions.*;

import com.laonstory.oauth.core.model.TokenModel;
import org.junit.jupiter.api.Test;

class JwtTokenProviderTest {

  @Test
  void generateToken() {
    JwtTokenProvider provider = new JwtTokenProvider(new JwtProperties());
    TokenModel unique = provider.getTokens("unique");
    assertEquals(unique.getTokenType(), "Bearer");
  }

}