package com.dragon.study.benchmark.utils.agent;

import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.utility.JavaModule;

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

import static net.bytebuddy.matcher.ElementMatchers.named;
/**
 * Created by dragon on 2017/1/26.
 */
@BenchmarkMode({Mode.Throughput})
@State(Scope.Benchmark)
public class ByteBuddy {

  private Transformer transformer;
  private NoTransformer noTransformer;

  @Benchmark
  public void transformerTest() throws Exception {
    transformer.sqrt("123");
  }

  @Benchmark
  public void noTransformerTest() throws Exception {
    noTransformer.sqrt("123");
  }

  @Setup
  public void setup() {
    ByteBuddyAgent.install();

    new AgentBuilder.Default().with(new AgentBuilder.Listener() {
      @Override
      public void onTransformation(TypeDescription typeDescription, ClassLoader classLoader,
          JavaModule module, boolean loaded, DynamicType dynamicType) {

      }

      @Override
      public void onIgnored(TypeDescription typeDescription, ClassLoader classLoader,
          JavaModule module, boolean loaded) {

      }

      @Override
      public void onError(String typeName, ClassLoader classLoader, JavaModule module,
          boolean loaded, Throwable throwable) {
        System.err.println(throwable);
      }

      @Override
      public void onComplete(String typeName, ClassLoader classLoader, JavaModule module,
          boolean loaded) {

      }
    }).type(
        named("com.dragon.study.benchmark.utils.agent.Transformer"))
        .transform(new AdviceTransformer()).installOnByteBuddyAgent();

    transformer = new Transformer();
    noTransformer = new NoTransformer();
  }


  public static void main(String[] args) throws RunnerException {
    Options opt = new OptionsBuilder().include(ByteBuddy.class.getSimpleName()).forks(1)
        .warmupIterations(2).measurementIterations(3).build();

    new Runner(opt).run();
  }
}
