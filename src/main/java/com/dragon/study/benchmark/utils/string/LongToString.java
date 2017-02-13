package com.dragon.study.benchmark.utils.string;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Random;

/**
 * Created by dragon on 2017/2/13.
 */
@BenchmarkMode({Mode.Throughput})
@State(Scope.Benchmark)
public class LongToString {

  private Random random = new Random();

  @Benchmark
  public String stringPlus() throws Exception {
    long l = random.nextLong();
    String s = l + "";
    return s;
  }

  @Benchmark
  public String stringValue() throws Exception {
    long l = random.nextLong();
    String s = String.valueOf(l);
    return s;
  }

  public static void main(String[] args) throws RunnerException {
    Options opt = new OptionsBuilder().include(LongToString.class.getSimpleName()).forks(1)
        .warmupIterations(1).measurementIterations(5).build();

    new Runner(opt).run();
  }

}
