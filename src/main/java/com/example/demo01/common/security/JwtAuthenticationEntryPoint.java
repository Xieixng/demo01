package com.example.demo01.common.security;


import cn.hutool.json.JSONUtil;
import com.example.demo01.common.Result;
import com.example.demo01.common.Resultcode;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证失败处理类
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        System.out.println("认证失败");

        Result result = new Result(Resultcode.UNAUTHRIZED,"认证失败");
//        result.setCode(500);
//        result.setMessage("认证失败");

        String json = JSONUtil.toJsonStr(result);

        httpServletResponse.setContentType("text/json;charset=utf-8");

        httpServletResponse.getWriter().write(json);

    }
}
