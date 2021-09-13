package com.laonstory.auth_provider.jwt.model;

import io.jsonwebtoken.Claims;
import java.util.Date;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GenerateTokenOption {
  private Claims claims;
  private Date now;
  private Date validityMinMilliseconds;
  private Date refreshValidityMinMilliseconds;
}
