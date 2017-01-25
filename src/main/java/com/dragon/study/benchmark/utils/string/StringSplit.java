package com.dragon.study.benchmark.utils.string;

import com.google.common.base.Splitter;

import org.apache.commons.lang3.StringUtils;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.List;
import java.util.StringTokenizer;
import java.util.UUID;

/**
 * Created by dragon on 2017/1/25.
 */
@BenchmarkMode({Mode.Throughput})
@State(Scope.Benchmark)
public class StringSplit {

  private String uuid = UUID.randomUUID().toString() + "-" + UUID.randomUUID()
      .toString() + "-" + UUID.randomUUID().toString() + "-" + UUID.randomUUID()
      .toString() + "-" + UUID.randomUUID().toString();

  @Benchmark
  public void stringSplit() throws Exception {
    StringTokenizer token = new StringTokenizer(uuid, "-");
    while (token.hasMoreElements()) {
      String first = (String) token.nextElement();
      break;
    }
  }

  @Benchmark
  public void guavaStringSplitIterable() throws Exception {
    Iterable<String> iterable = Splitter.on("-").split(uuid);
    String first = iterable.iterator().next();
  }

  @Benchmark
  public void guavaStringSplitList() throws Exception {
    List<String> list = Splitter.on("-").splitToList(uuid);
    String first = list.get(0);
  }

  @Benchmark
  public void apacheStringSplit() throws Exception {
    String[] array = StringUtils.split(uuid, "-");
    String first = array[0];
  }

  @Benchmark
  public void apacheStringSplitPreserveAllTokens() throws Exception {
    String[] array = StringUtils.splitPreserveAllTokens(uuid, "-");
    String first = array[0];
  }


  public static void main(String[] args) throws RunnerException {
    Options opt = new OptionsBuilder().include(StringSplit.class.getSimpleName()).forks(1)
        .warmupIterations(1).measurementIterations(3).build();

    new Runner(opt).run();
  }

}
