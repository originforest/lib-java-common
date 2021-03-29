/**
* Copyright (c) 2020 Copyright Originforest All Rights Reserved.
* Author: brian.li
* Date: 2020-08-04 17:55
* Desc: 
*/
package com.originforest.common.utils;

public class StringUtils {

  /**
   * 判断参数是否为null或空字符串.
   * 
   * @param v 字符串
   * @return 是否为空
   */
  public static boolean isEmpty(String v) {
    return v == null || v.isEmpty();
  }
}