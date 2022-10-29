package com.example.demo01.common.config;

import com.example.demo01.common.security.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import sun.security.pkcs11.P11Util;

import javax.annotation.Resource;
import java.security.PublicKey;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(proxyTargetClass = true,securedEnabled = true)

public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private JwtAuthenticationEntryPoint jwtAuthenticaEntryPoint;

    @Resource
    private JwtAccessDeinedHanler jwtAccessDeinedHanler;

    @Resource
    private JwtAuthenticationSuccessHandler jwtAuthenticationSuccessHandler;

    @Resource
    private LoginFailureHandler loginFailurHandler;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthenticationTokenFilter authenticationTokenFilterBean() throws  Exception{
        return new JwtAuthenticationTokenFilter();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .formLogin()
            //自定义认证
            .successHandler(jwtAuthenticationSuccessHandler)
            //自定义失败拦截器
            .failureHandler(loginFailurHandler)
            //自定义登录拦截地址
            .loginProcessingUrl("/login")
            .and()
            //自定义认证失败类
            .exceptionHandling().authenticationEntryPoint(jwtAuthenticaEntryPoint)
            //自定义权限不足处理类
            .accessDeniedHandler(jwtAccessDeinedHanler)
            .and()
            //设置无状态，不创建session
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests()
            .antMatchers(HttpMethod.OPTIONS,"/**").permitAll()
            .antMatchers("/logic").permitAll()
            .anyRequest().authenticated();


        //允许跨域
        http.cors().and().csrf().disable();


        //配置jwt校验过滤器
        http.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);

        //缓存
        http.headers().cacheControl();

    }


}