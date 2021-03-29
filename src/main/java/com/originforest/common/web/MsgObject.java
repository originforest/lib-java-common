/**
* Copyright (c) 2020 Copyright Originforest All Rights Reserved.
* Author: lipengxiang
* Date: 2020-07-08 13:59
* Desc: 
*/
package com.originforest.common.web;

public class MsgObject extends Msg<Object> {
  public MsgObject() { super(); }
  public MsgObject(ErrorCode err) { super(err); }
  public MsgObject(ErrorCode err, final String errMsg) { super(err, errMsg); }
}
