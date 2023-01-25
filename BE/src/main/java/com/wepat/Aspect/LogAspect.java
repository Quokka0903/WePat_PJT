package com.wepat.Aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LogAspect {

    private Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Before(value = "execution(* com.wepat..*.*(..))") //어떤 메서드에 적용할지를 표현식에 서술
    public void beforeMethod(JoinPoint joinPoint) {
        logger.info("Called " + joinPoint.getSignature());
        logger.info("params >> " + Arrays.toString(joinPoint.getArgs()));
    }

    @After(value = "execution(* com.wepat..*.*(..))") //어떤 메서드에 적용할지를 표현식에 서술
    public void afterMethod(JoinPoint joinPoint) {
        logger.info("Done " + joinPoint.getSignature().getName());
        //공통기능
    }

//	@Around(value = "execution(* com.ssafy.myapp.model..*.*(..))") //어떤 메서드에 적용할지를 표현식에 서술
//	@Around(value = "execution(* com.ssafy.myapp.model.dao..*.*(..))") //DAO에 있는 메서드에만 적용
//	@Around(value = "execution(* com.ssafy.myapp.model.dao..BoardDAOImpl.*(..))") //BoardDAO에 있는 메서드에만 적용
//	@Around(value = "execution(* com.ssafy.myapp.model.dao..Board*.*(..))") //BoardDAO에 있는 메서드에만 적용
//    @Around(value = "execution(* com.ssafy.myapp.model.dao..Board*.*Board(..))") //BoardDAO에 있는 delete,update메서드 에만 적용
//    public Object aroundMethod(ProceedingJoinPoint joinPoint) throws Throwable {
//        //공통기능
//        StopWatch stopWatch = new StopWatch();
//
//        //전처리
//        stopWatch.start();
//
//        Object proceed = joinPoint.proceed(); //핵심코드(메서드) 호출
//
//        stopWatch.stop();
//        //후처리
//        logger.debug("pretty: {}", stopWatch.prettyPrint());
//        logger.debug("totalTime: {}", stopWatch.getTotalTimeMillis());
//        logger.debug("메서드정보: {}", joinPoint.getSignature());
//
//        return proceed; //또다른 설정 AOP 호출
//    }
}
