package com.laonstory.oauth.core.interfaces;

/**
 * Created by Aaron on 2021/09/06
 */
public interface Caller<T> {

  boolean validate();
  T getProfile();

  void logout();

}
