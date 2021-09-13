package com.laonstory.oauth.core.util;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import org.junit.jupiter.api.Test;

class DateUtilsTest {

  @Test
  void convertMillisecondToSecondMinus_1() {
    long millisecond = 11010;

    long l = millisecond / 1000;

    assertEquals(l, 11);
  }

}