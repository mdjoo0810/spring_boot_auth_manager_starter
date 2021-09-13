package com.laonstory.oauth.core.util;

import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestOperations;

class RestTemplateFactoryTest {

  private static RestOperations restOperations;
  private static ObjectMapper objectMapper;

  @BeforeAll
  static void init() {
    HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
    factory.setConnectTimeout(1000);
    factory.setReadTimeout(1000);

    restOperations = RestTemplateFactory.getRestOperations(factory);
    objectMapper = new ObjectMapper();
  }

  @Test
  void createFactory() {

    assertNotNull(restOperations);
  }

  @Test
  void getTest() throws IOException {
    String testUrl = "https://random-word-api.herokuapp.com/word?number=1";

    List forObject = restOperations.getForObject(testUrl, List.class);

    assertNotNull(forObject.get(0));

  }

}