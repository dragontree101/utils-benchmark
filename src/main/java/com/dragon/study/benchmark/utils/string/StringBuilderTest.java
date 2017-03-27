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

import java.util.UUID;

/**
 * Created by dragon on 2017/3/26.
 */

@BenchmarkMode({Mode.Throughput})
@State(Scope.Benchmark)
public class StringBuilderTest {

  private StringBuilder sb1 = new StringBuilder();

  @Benchmark
  public void newBuilderTest() throws Exception {
    sb1.append(UUID.randomUUID().toString()).append(UUID.randomUUID().toString())
        .append(UUID.randomUUID().toString()).append(UUID.randomUUID().toString())
        .append(UUID.randomUUID().toString()).append(UUID.randomUUID().toString())
        .append(UUID.randomUUID().toString());
    sb1 = new StringBuilder();
  }

  @Benchmark
  public void deleteTest() throws Exception {
    sb1.append(UUID.randomUUID().toString()).append(UUID.randomUUID().toString())
        .append(UUID.randomUUID().toString()).append(UUID.randomUUID().toString())
        .append(UUID.randomUUID().toString()).append(UUID.randomUUID().toString())
        .append(UUID.randomUUID().toString());
    sb1.delete(0, sb1.length());
  }

  @Benchmark
  public void setLengthTest() throws Exception {
    sb1.append(UUID.randomUUID().toString()).append(UUID.randomUUID().toString())
        .append(UUID.randomUUID().toString()).append(UUID.randomUUID().toString())
        .append(UUID.randomUUID().toString()).append(UUID.randomUUID().toString())
        .append(UUID.randomUUID().toString());
    sb1.setLength(0);
  }

  public static void main(String[] args) throws RunnerException {
    Options opt = new OptionsBuilder().include(StringBuilderTest.class.getSimpleName()).forks(1)
        .warmupIterations(2
        ).measurementIterations(5).build();

    new Runner(opt).run();
  }
}
