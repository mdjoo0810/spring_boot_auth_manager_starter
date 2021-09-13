package com.laonstory.auth_provider.kakao;

import com.laonstory.oauth.core.interfaces.Caller;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class KakaoCaller implements Caller<KakaoProfile> {

  private static final String PROVIDER = "KAKAO";

  @Override
  public boolean validate() {
    return false;
  }

  @Override
  public KakaoProfile getProfile() {
    return null;
  }

  @Override
  public void logout() {

  }
}
