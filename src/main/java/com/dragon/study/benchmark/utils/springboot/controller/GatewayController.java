package com.dragon.study.benchmark.utils.springboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by dragon on 2017/2/4.
 */
@RestController
@RequestMapping("/")
public class GatewayController {

  @RequestMapping(value = "/get", method = RequestMethod.GET)
  public boolean get() {
    return true;
  }

  @RequestMapping(value = "/post.json", method = RequestMethod.POST)
  public boolean post() {
    return true;
  }
}
