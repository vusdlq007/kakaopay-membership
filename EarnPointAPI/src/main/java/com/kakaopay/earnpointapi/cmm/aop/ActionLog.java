package com.kakaopay.earnpointapi.cmm.aop;


import com.kakaopay.earnpointapi.api.dto.EarnResponseDTO;
import com.kakaopay.earnpointapi.api.svc.ActionLogService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Component
@Slf4j
@Aspect
public class ActionLog {

    @Autowired
    ActionLogService actionLogService;


    // 해당방식은 가로챈 메서드에 리턴타입이 있다면 Around에서 받아서 직접 리턴하기때문에 리턴값도 정해줘야한다.
    @Around("within(com.kakaopay.earnpointapi.api.ctr..*)")
    @Transactional
    public Object around(ProceedingJoinPoint jpt) throws Throwable {
        Object responseDTO = null;
        try {
            log.info("로깅 액션 시작.");

            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

            String httpMethod = request.getMethod();

            // 핵심기능 메소드 수행
            responseDTO = jpt.proceed();

            // 정상 처리가 아닐떈 로깅 로직 skip..
            if (((EarnResponseDTO) responseDTO).getResCode() != 200){
                return responseDTO;
            }

            try {
                actionLogService.createAction((EarnResponseDTO) responseDTO);
            } catch (Exception e) {
                e.getMessage();
                e.printStackTrace();
                log.error("UserAction 로깅 중 에러발생. 로깅 스킵...");
            }


        } catch (Exception e) {
            log.error("AOP ERROR Message [" + e.getMessage() + "]");
            e.printStackTrace();
            log.error("AOP 실행중 에러발생.");
        } finally {
            log.debug("로깅 액션 종료.");
        }
        return responseDTO;
    }

    /**
     * HTTP METHOD에 따른 액션 설정.
     *
     * @param httpMethod
     * @return
     */
    public String setHttpActionType(String httpMethod) {
        String action = "";

        switch (httpMethod.toUpperCase()) {
            case "POST":
                action = "생성";
                break;
            case "PUT":
                action = "수정";
                break;
            case "DELETE":
                action = "삭제";
                break;
            default:
                log.error("요청 HTTP METHOD와 일치하는 액션이 없습니다.");
                break;
        }
        return action;
    }
}
