package com.joe.exception;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.api.R;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.joe.common.entity.Result;
import com.joe.common.entity.ResultCode;
import com.joe.utils.JwtTokenUtils;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * Token验证过滤器
 */
public class JwtAuthenticationFilter extends OncePerRequestFilter {
 
    public JwtAuthenticationFilter() {

    }
 
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        httpServletResponse.setContentType("application/json");
        String authorization = httpServletRequest.getHeader("Authorization");
        // 放行GET请求
        if (httpServletRequest.getMethod().equals(String.valueOf(RequestMethod.GET))) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }
        if (StringUtils.isEmpty(authorization)) { // 未提供Token
            httpServletResponse.getWriter().write(JSON.toJSONString(new Result(ResultCode.UNAUTHORISE,"未提供令牌")));
            return;
        }
        if (!authorization.startsWith("bearer ")) { // Token格式错误
            httpServletResponse.getWriter().write(JSON.toJSONString(new Result(ResultCode.UNAUTHORISE,"令牌格式错误")));
            return;
        }
        authorization = authorization.replace("bearer ", "");
        Claims claims = JwtTokenUtils.getAllClaimsFromToken(authorization);
        if (null == claims) { // Token不可解码
            httpServletResponse.getWriter().write(JSON.toJSONString(new Result(ResultCode.UNAUTHORISE,"无法解析令牌")));
            return;
        }
        if (claims.getExpiration().getTime() >= new Date().getTime()) { // Token超时
            httpServletResponse.getWriter().write(JSON.toJSONString(new Result(ResultCode.UNAUTHORISE,"令牌已过期")));
            return;
        }
        // 再进行一些必要的验证
        if (StringUtils.isEmpty(claims.getSubject())) {
            httpServletResponse.getWriter().write(JSON.toJSONString(new Result(ResultCode.UNAUTHORISE,"令牌无效")));
            return;
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}