package com.laonstory.oauth;

import com.laonstory.auth_provider.jwt.JwtTokenProvider;
import com.laonstory.auth_provider.jwt.model.TokenModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {


  private final JwtTokenProvider tokenProvider;

  public TestController(JwtTokenProvider tokenProvider) {
    this.tokenProvider = tokenProvider;
  }

  @GetMapping
  public ResponseEntity<TokenModel> getToken() {
    return ResponseEntity.ok(tokenProvider.getTokens("test"));
  }

}
