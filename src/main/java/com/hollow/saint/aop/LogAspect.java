package com.hollow.saint.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Aspect
@Component
public class LogAspect {

	@Resource
	HttpServletRequest request;

	List<String> list = new ArrayList<>();

	@Around("@annotation(LogAnnotation))")
	public Object around(ProceedingJoinPoint pjp) throws Throwable {
		// 方法签名
		MethodSignature signature = (MethodSignature) pjp.getSignature();
		if (signature.getMethod().isAnnotationPresent(LogAnnotation.class)) {
			LogAnnotation logAnnotation = signature.getMethod().getAnnotation(LogAnnotation.class);
			// 日志内容
			String module = logAnnotation.module();
			String operate = logAnnotation.operate();
			list.add(module);
			list.add(operate);
		}
		// 类信息
		Object targetClass = pjp.getTarget();
		String className = targetClass.getClass().getName();
		String method = targetClass.getClass().getMethod(signature.getName(), signature.getParameterTypes()).getName();
		String args = Arrays.stream(pjp.getArgs()).filter(Objects::nonNull).map(Object::toString).collect(Collectors.joining(","));
		list.add(className);
		list.add(method);
		list.add(args);
		// ip
		String ip = request.getRemoteAddr();
		list.add(ip);
		// 时间
		long start = System.currentTimeMillis();
		list.add(String.valueOf(start));
		try {
			Object object = pjp.proceed();
			long end = System.currentTimeMillis();
			long total = end - start;
			list.add(String.valueOf(end));
			list.add(String.valueOf(total));
			System.out.println(list);
			return object;
		} catch (Throwable throwable) {
			throwable.printStackTrace();
		}
		return null;
	}
}
