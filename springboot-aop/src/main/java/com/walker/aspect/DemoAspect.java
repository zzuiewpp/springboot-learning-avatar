package com.walker.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author walker
 * @version 1.0
 * @since 2018-10-03 19:27
 */
@Component
@Aspect
public class DemoAspect {
    @Around(value = "@annotation(org.springframework.web.bind.annotation.PostMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.GetMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.PutMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.DeleteMapping)")
    public Object around(ProceedingJoinPoint pjp) {
        try {
            try {
                Signature signature = pjp.getSignature();
                MethodSignature methodSignature = (MethodSignature) signature;
                Method method = methodSignature.getMethod();
                String methodName = method.getName();
                String className = methodSignature.getDeclaringTypeName();
                System.out.println("executing " + className + "----" + methodName);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Object object = pjp.proceed();
            return object;
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return "exception...";
    }
}
