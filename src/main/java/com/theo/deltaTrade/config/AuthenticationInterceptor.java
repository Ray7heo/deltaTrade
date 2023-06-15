package com.theo.deltaTrade.config;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.InvalidClaimException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.theo.deltaTrade.util.Token;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import java.util.HashMap;
import java.util.Map;

@Configuration
@Slf4j
public class AuthenticationInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.warn(handler.toString());
        String token = request.getHeader("token");
        Map<String, Object> map = new HashMap<>();
        //放行静态资源
        if (handler instanceof ResourceHttpRequestHandler) {
            return true;
        }
        try {
            Token.parse(token);
            return true;
        } catch (SignatureVerificationException e) {
            e.printStackTrace();
            map.put("msg", "签名不一致");
        } catch (TokenExpiredException e){
            e.printStackTrace();
            map.put("msg", "令牌过期");
        } catch(AlgorithmMismatchException e){
            e.printStackTrace();
            map.put("msg", "算法不匹配");
        } catch(InvalidClaimException e){
            e.printStackTrace();
            map.put("msg", "失效的payload");
        } catch(Exception e){
            e.printStackTrace();
            map.put("msg", "token无效");
        }
        map.put("code", 401);
        String json = new ObjectMapper().writeValueAsString(map);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(json);
        return false;
    }
}
