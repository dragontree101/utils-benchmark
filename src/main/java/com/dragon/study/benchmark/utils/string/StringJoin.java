package com.dragon.study.benchmark.utils.string;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

import org.apache.commons.lang3.StringUtils;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.List;

/**
 * Created by dragon on 2017/1/25.
 */
@BenchmarkMode({Mode.Throughput})
@State(Scope.Benchmark)
public class StringJoin {

  private List<String> list = Lists.newArrayList("1", "2", "3", "4", "5");

  @Benchmark
  public void guavaStringJoinChar() throws Exception {
    String joinString = Joiner.on(',').join(list);
  }

  @Benchmark
  public void guavaStringJoinString() throws Exception {
    String joinString = Joiner.on(",").join(list);
  }

  @Benchmark
  public void apacheStringJoinChar() throws Exception {
    String joinString = StringUtils.join(list, ',');
  }

  @Benchmark
  public void apacheStringJoinString() throws Exception {
    String joinString = StringUtils.join(list, ",");
  }

  @Benchmark
  public void stringJoin() throws Exception {
    String joinString = String.join(",", list);
  }


  public static void main(String[] args) throws RunnerException {
    Options opt = new OptionsBuilder().include(StringJoin.class.getSimpleName()).forks(1)
        .warmupIterations(1).measurementIterations(3).build();

    new Runner(opt).run();
  }

}
