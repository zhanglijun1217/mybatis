/*
 *    Copyright 2009-2012 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.apache.ibatis.reflection.invoker;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Clinton Begin
 */
/**
 * 方法调用者
 * 
 */
public class MethodInvoker implements Invoker {

  private Class<?> type;
  private Method method;

  /**
   * MethodInvoker 构造函数
   * type getter方法时为返回值类型 setter方法时为参数类型
   * Reflector中type类字段有setter getter方法的字段生成的invoker
   *
   * @param method
   */
  public MethodInvoker(Method method) {
    this.method = method;

    //如果只有一个参数，返回参数类型，否则返回return的类型
    if (method.getParameterTypes().length == 1) {
      type = method.getParameterTypes()[0];
    } else {
      type = method.getReturnType();
    }
  }

  //就是调用Method.invoke
  @Override
  public Object invoke(Object target, Object[] args) throws IllegalAccessException, InvocationTargetException {
    return method.invoke(target, args);
  }

  @Override
  public Class<?> getType() {
    return type;
  }
}
