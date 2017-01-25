package com.dragon.study.benchmark.utils.string;

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

/**
 * Created by dragon on 2017/1/25.
 */
@BenchmarkMode({Mode.Throughput})
@State(Scope.Benchmark)
public class StringReplace {

  private String oneChar = "-";

  private String someChars = "aksjkfnvunriunr,nfnweghiwenfiwegwevmsdn,mnwoqfnwjfbwgywuefweksnddm,sdbgwegbwuiasd";

  @Benchmark
  public void oneCharReplaceOneChar() throws Exception {
    String replaced = oneChar.replace('-', '_');
  }

  @Benchmark
  public void oneCharReplaceString() throws Exception {
    String replaced = oneChar.replace("-", "_");
  }

  @Benchmark
  public void someCharsApacheReplace() throws Exception {
    String replaced = StringUtils.replace(someChars, "nwjf", "1");
  }

  @Benchmark
  public void someCharsApacheReplaceChars() throws Exception {
    String replaced = StringUtils.replaceChars(someChars, "nwjf", "1");
  }

  @Benchmark
  public void someCharsReplace() throws Exception {
    String replaced = someChars.replace("nwjf", "1");
  }

  public static void main(String[] args) throws RunnerException {
    Options opt = new OptionsBuilder().include(StringReplace.class.getSimpleName()).forks(1)
        .warmupIterations(1).measurementIterations(3).build();

    new Runner(opt).run();
  }

}
