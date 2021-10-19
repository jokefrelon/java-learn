package top.jokeme.log;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class logger  implements MethodBeforeAdvice, AfterReturningAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
//         method: 要对那个方法下手
//           args：不用管
//         target：目标对象

        System.out.println(target.getClass().getName()+"----"+method.getName());

    }

    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
//         参数意义都一样，多的 returningValue 表示返回值
        System.out.println(target.getClass().getName()+"---"+method.getName()+returnValue);
    }
}
