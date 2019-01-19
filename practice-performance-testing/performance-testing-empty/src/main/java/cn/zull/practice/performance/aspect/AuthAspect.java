package cn.zull.practice.performance.aspect;

import cn.zull.practice.common.basis.constants.AuthType;
import cn.zull.practice.common.basis.constants.IflytekAuth;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @author zurun
 * @date 2019/1/19 21:34:15
 */
@Aspect
@Component
@Slf4j
public class AuthAspect {
    @Pointcut(value = "@annotation(cn.zull.practice.common.basis.constants.IflytekAuth)")
    public void aspect() {
    }

    @Around(value = "aspect()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String appid = request.getHeader("appid");

        //切点所在的类
        Class targetClass = pjp.getTarget().getClass();
        //使用了注解的方法
        String methodName = pjp.getSignature().getName();
        Class[] parameterTypes = ((MethodSignature) pjp.getSignature()).getMethod().getParameterTypes();
        Method method = targetClass.getMethod(methodName, parameterTypes);
        AuthType authType = method.getAnnotation(IflytekAuth.class).authType();

        return pjp.proceed();
    }
}
