package com.dragon.study.benchmark.utils.agent;

import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.matcher.ElementMatcher;

import static net.bytebuddy.matcher.ElementMatchers.isPublic;
import static net.bytebuddy.matcher.ElementMatchers.named;
import static net.bytebuddy.matcher.ElementMatchers.returns;
import static net.bytebuddy.matcher.ElementMatchers.takesArguments;

/**
 * Created by dragon on 16/4/18.
 */
public class AdviceTransformer extends AbstractAdviceTransformer {

  @Override
  protected Class<?> builderAdviceTransform() {
    return AdviceInterceptor.class;
  }

  @Override
  protected ElementMatcher<? super MethodDescription> description(
      ClassFileLocator.Compound compound) {
    return named("sqrt").and(isPublic()).and(takesArguments(TypeDescription.STRING))
        .and(returns(TypeDescription.VOID));
  }

}
