package com.yundong.usercenter.aop;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.yundong.base.exception.BizException;
import com.yundong.base.result.Result;
import com.yundong.base.result.ResultCodes;
import com.yundong.usercenter.enums.LoggerEnums;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.hibernate.validator.internal.engine.ConstraintViolationImpl;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolationException;
import java.lang.reflect.Method;
import java.util.Objects;

@Aspect
@Component
public class DubboServiceAspect {

    private static final Logger serviceLog = LoggerEnums.SERVICE_LOG.getLogger();

    @Pointcut("execution(public * com.yundong.usercenter.provider.*.*(..))")
    public void dubboService() {
    }

    @Around("dubboService()")
    public Object logAndHandleException(ProceedingJoinPoint jp) {
        long start = System.currentTimeMillis();
        Object result = null;
        Throwable throwable = null;
        //获取方法参数值数组
        Object[] args = jp.getArgs();
        Method method = ((MethodSignature) jp.getSignature()).getMethod();
        String className = method.getDeclaringClass().getName();
        String methodName = method.getName();

        try {
            result = jp.proceed();
        } catch (ConstraintViolationException e) {
            result = new Result<>(ResultCodes.INVALID_PARAM, this.getViolationErrorMsg(e));
        } catch (BizException e) {
            result = new Result<>(e.getResultCode());
        } catch (Throwable t) {
            // 未知异常
            throwable = t;
            result = new Result<>(ResultCodes.SERVER_ERROR);
        } finally {
            long costMs = System.currentTimeMillis() - start;
            serviceLog.error("{}|{}|{}|{}|{}|{}", className, methodName, costMs, JSON.toJSONString(args, SerializerFeature.WriteMapNullValue),
                    JSON.toJSONString(result, SerializerFeature.WriteMapNullValue), Objects.isNull(throwable) ? "" : throwable);
        }
        return result;
    }


    private String getViolationErrorMsg(ConstraintViolationException e) {
        String message = ResultCodes.INVALID_PARAM.getMessage();
        try {
            message = ((ConstraintViolationImpl<?>) e.getConstraintViolations().toArray()[0]).getMessage();
        } catch (Throwable ignored) {
        }
        return message;
    }


}
