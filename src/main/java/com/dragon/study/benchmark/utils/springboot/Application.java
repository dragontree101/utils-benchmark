package com.dragon.study.benchmark.utils.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by dragon on 2017/2/4.
 */
@SpringBootApplication
public class Application {
  public static void main(String[] args) {
    SpringApplication app = new SpringApplication(Application.class);
    app.run(args);
  }
}
