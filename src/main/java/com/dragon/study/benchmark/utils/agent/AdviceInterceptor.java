package com.dragon.study.benchmark.utils.agent;

import net.bytebuddy.asm.Advice;

import java.util.HashMap;
import java.util.Map;

public class AdviceInterceptor {

  @Advice.OnMethodEnter(suppress = Throwable.class)
  public static Map<String, Object> enter(
      @Advice.Argument(value = 0)
          String arg1) {
    Map<String, Object> mapToExit = new HashMap<>();
    mapToExit.put("result", arg1);
    return mapToExit;
  }

  @Advice.OnMethodExit(onThrowable = Throwable.class, suppress = Throwable.class)
  public static void exit(
      @Advice.Enter
          Map<String, Object> enterParam,
      @Advice.Thrown
          Throwable t) {
    enterParam.get("result");
  }

}
