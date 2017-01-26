package com.dragon.study.benchmark.utils.agent;

import java.util.Random;

/**
 * Created by dragon on 2017/1/26.
 */
public class Transformer {

  public void sqrt(String num) {
    Random random = new Random();
    for(int i = 0; i < 100; i++) {
      double result = Math.sqrt(Integer.valueOf(num) + random.nextInt(100));
    }
  }
}
