package com.example.demo01.common.security;

import cn.hutool.json.JSONUtil;
import com.example.demo01.common.Result;
import com.example.demo01.common.Resultcode;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证失败
 */
@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        returnFailure(httpServletResponse);
    }
    public void returnFailure(HttpServletResponse httpServletResponse) throws IOException{
        httpServletResponse.setContentType("text/json;charset=utf-8" );
        httpServletResponse.getWriter().write(JSONUtil.toJsonStr(new Result(Resultcode.UNAUTHRIZED,"认证失败")));
    }
}