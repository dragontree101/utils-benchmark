package com.dragon.study.benchmark.utils.random;

import org.apache.commons.lang3.RandomUtils;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.IntStream;

/**
 * Created by dragon on 2017/1/26.
 */
@BenchmarkMode({Mode.Throughput})
@State(Scope.Benchmark)
public class IntRandom {

  private Random random;

  private ThreadLocalRandom threadLocalRandom;

  private LongAdder adder;

  private final long loopTimes = 100000000L;

  private final int threadNum = 10;


  @Benchmark
  public void commonRandom() throws Exception {
    random.nextInt();
  }

  @Benchmark
  public void threadLocalRandom() throws Exception {
    threadLocalRandom.nextInt();
  }

  @Benchmark
  public void apacheRandom() throws Exception {
    RandomUtils.nextInt();
  }

  @Benchmark
  @BenchmarkMode({Mode.AverageTime})
  public void commonRandomTime() throws Exception {
    ExecutorService executorService = Executors.newFixedThreadPool(threadNum);
    IntStream.range(0, threadNum).forEach(i -> executorService.submit(() -> {
      while (adder.longValue() < loopTimes) {
        adder.increment();
        random.nextInt();
      }
    }));
    IntRandom.stop(executorService);
  }

  @Benchmark
  @BenchmarkMode({Mode.AverageTime})
  public void threadLocalRandomTime() throws Exception {
    ExecutorService executorService = Executors.newFixedThreadPool(threadNum);
    IntStream.range(0, threadNum).forEach(i -> executorService.submit(() -> {
      while (adder.longValue() < loopTimes) {
        adder.increment();
        ThreadLocalRandom.current().nextInt();
      }
    }));
    IntRandom.stop(executorService);
  }

  @Setup
  public void setup() {
    random = new Random();
    adder = new LongAdder();
    threadLocalRandom = ThreadLocalRandom.current();
  }


  private static void stop(ExecutorService executor) {
    try {
      executor.shutdown();
      executor.awaitTermination(60, TimeUnit.SECONDS);
    } catch (InterruptedException e) {
      System.err.println("termination interrupted");
    } finally {
      if (!executor.isTerminated()) {
        System.err.println("killing non-finished tasks");
      }
      executor.shutdownNow();
    }
  }

  public static void main(String[] args) throws RunnerException {
    Options opt = new OptionsBuilder().include(IntRandom.class.getSimpleName()).forks(1)
        .warmupIterations(1).measurementIterations(3).build();

    new Runner(opt).run();
  }
}
