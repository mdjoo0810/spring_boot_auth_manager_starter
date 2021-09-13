package com.laonstory.oauth.core.util;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Aaron on 2021/09/06
 */
public class RestTemplateFactory {

  public static RestOperations getRestOperations(HttpComponentsClientHttpRequestFactory factory) {

    RestTemplate restTemplate = new RestTemplate(factory);

    StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter(
        StandardCharsets.UTF_8);
    MappingJackson2HttpMessageConverter jackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
    ByteArrayHttpMessageConverter byteArrayHttpMessageConverter = new ByteArrayHttpMessageConverter();
    FormHttpMessageConverter formHttpMessageConverter = new FormHttpMessageConverter();
    formHttpMessageConverter.setCharset(StandardCharsets.UTF_8);

    List<HttpMessageConverter<?>> converters = new ArrayList<>();
    converters.add(stringHttpMessageConverter);
    converters.add(jackson2HttpMessageConverter);
    converters.add(byteArrayHttpMessageConverter);
    converters.add(formHttpMessageConverter);

    restTemplate.setMessageConverters(converters);

    return restTemplate;

  }

}
