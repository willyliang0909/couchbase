package com.ecom.demo.couchbase.aop;

import com.ecom.demo.couchbase.dao.TokenDAOImpl;
import com.ecom.demo.couchbase.model.Token;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
@Aspect
public class TokenAop {

    private final TokenDAOImpl tokenDAO;

    @Pointcut("@annotation(tokenUpdate)")
    public void annotationPointCut(TokenUpdate tokenUpdate) {
    }

    @AfterReturning(pointcut = "annotationPointCut(tokenUpdate)", returning = "result")
    public void afterReturning(JoinPoint joinPoint, TokenUpdate tokenUpdate, Object result) {

        boolean bypassCache = false; //CacheUtil.bypassCache
        if (bypassCache){
            return;
        }

        Object[] args = joinPoint.getArgs();

        if (args[0] instanceof Token) {
            Token token = (Token) args[0];
            String name = joinPoint.getSignature().getName();
            if (name.equals("insertToken")) {
                log.info("afterReturning -- insertToken");
                //tokenDAO.insertToken(token);
            } else if (name.equals("updateToken")) {
                log.info("afterReturning -- updateToken");
                //tokenDAO.updateToken(token);
            }
        }
    }
}
