package com.dragon.study.benchmark.utils.collection;

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

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by dragon on 2017/1/26.
 */
@BenchmarkMode({Mode.Throughput})
@State(Scope.Benchmark)
public class CollectionEnum {

  public enum Color
  {
    A,B,C,D
  }

  @Benchmark
  public void commonMapInit() throws Exception {
    Map<String, String> map = new HashMap<String, String>() {
      {
        put("a", "1");
        put("b", "2");
        put("c", "3");
        put("d", "4");
      }
    };
  }

  @Benchmark
  public void enumMapInit() throws Exception {
    EnumMap<Color,String> map = new EnumMap<>(Color.class);
    map.put(Color.A, "1");
    map.put(Color.B, "2");
    map.put(Color.C, "3");
    map.put(Color.D, "4");
  }

  @Benchmark
  public void commonSetInit() throws Exception {
    Set<String> set = new HashSet<String>() {
      {
        add("a");
        add("b");
        add("c");
        add("d");
      }
    };
  }

  @Benchmark
  public void enumSetInit1() throws Exception {
    EnumSet<Color> set = EnumSet.of(Color.A, Color.B, Color.C, Color.D);
  }

  @Benchmark
  public void enumSetInit2() throws Exception {
    EnumSet<Color> set = EnumSet.allOf(Color.class);
  }

  public static void main(String[] args) throws RunnerException {
    Options opt = new OptionsBuilder().include(CollectionEnum.class.getSimpleName()).forks(1)
        .warmupIterations(1).measurementIterations(3).build();

    new Runner(opt).run();
  }
}
