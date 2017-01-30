package com.dragon.study.benchmark.utils.logger;

import com.google.common.base.Joiner;

import com.dragon.study.benchmark.utils.string.StringJoin;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by dragon on 2017/1/29.
 */
@BenchmarkMode({Mode.Throughput})
@State(Scope.Benchmark)
@Slf4j
public class LogBackInfo {

  private String[] array = {"a", "b", "c", "d", "e"};

  @Benchmark
  public void infoTest1() throws Exception {
    log.info("a is {}, b is {}, c is {}, d is {}, e is {}", array[0], array[1], array[2], array[3], array[4]);
  }

  @Benchmark
  public void infoTest2() throws Exception {
    log.info("a is " + array[0] + ", b is "+ array[1] +", c is "+ array[2] +", d is "+ array[3] +", e is "+ array[4]);
  }

  public static void main(String[] args) throws RunnerException {
    Options opt = new OptionsBuilder().include(LogBackInfo.class.getSimpleName()).forks(1)
        .warmupIterations(1).measurementIterations(3).build();

    new Runner(opt).run();
  }
}
