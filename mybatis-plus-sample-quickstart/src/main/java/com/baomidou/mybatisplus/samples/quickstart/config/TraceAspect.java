package com.baomidou.mybatisplus.samples.quickstart.config;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import brave.Tracer;
import lombok.extern.slf4j.Slf4j;

@Aspect // 定义一个切面
@Configuration
@Slf4j
public class TraceAspect {
	// 定义切点Pointcut
	@Pointcut("execution(* com.baomidou.mybatisplus.samples.quickstart.controller..*.*(..))")
	public void excudeService() {
	}

	@Autowired
	Tracer tracer;

	@Around("excudeService()")
	public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
		RequestAttributes ra = RequestContextHolder.getRequestAttributes();
		ServletRequestAttributes sra = (ServletRequestAttributes) ra;
		HttpServletRequest request = sra.getRequest();

		String url = request.getRequestURL().toString();
		String method = request.getMethod();
		String uri = request.getRequestURI();
		String queryString = request.getQueryString();
		log.info("请求开始, 各个参数, url: {}, method: {}, uri: {}, params: {}", url, method, uri, queryString);
		// result的值就是被拦截方法的返回值
		Object result = pjp.proceed();
		
		if(result instanceof Collection) {
			JSONArray dd = JSONArray.parseArray(JSON.toJSONString(result));
			//dd.put("traceId", tracer.currentSpan().context().traceIdString());

			log.info("请求结束，controller的返回值是 {} - {}", tracer.currentSpan().toString(), dd.toJSONString());
			return dd;
		}else
		{
			JSONObject dd = JSONObject.parseObject(JSON.toJSONString(result));
			dd.put("traceId", tracer.currentSpan().context().traceIdString());

			log.info("请求结束，controller的返回值是 {} - {}", tracer.currentSpan().toString(), dd.toJSONString());
			return dd;
		}

		
	}
}
