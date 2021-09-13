package com.laonstory.oauth.core.util;

import java.util.Date;

public class DateUtils {

  public static Integer convertMillisecondToSecond(long millisecond) {
    return (int) (millisecond / 1000);
  }

}
