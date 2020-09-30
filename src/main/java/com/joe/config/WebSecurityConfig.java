package com.joe.config;

import com.joe.exception.JWTAccessDeniedHandler;
import com.joe.exception.JWTAuthenticationEntryPoint;
import com.joe.filter.JWTAuthenticationFilter;
import com.joe.filter.JWTAuthorizationFilter;
import com.joe.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private MyFilterSecurityInterceptor myFilterSecurityInterceptor;

    //密码编码器
    @Bean
    public PasswordEncoder passwordEncoder(){
        //return NoOpPasswordEncoder.getInstance(); 明文
        return new LoginPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder()); //user Details Service验证

    }

    //安全拦截机制（最重要）
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests()
                .anyRequest().authenticated() //任何请求,登录后可以访问
                //.and()
                //.formLogin()
                //.loginPage("/auth/login")
                //.failureUrl("/auth/login?error")
                //.permitAll() //登录页面用户任意访问
                .and()
                .logout().permitAll() //注销行为任意访问
                .and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
                .addFilter(new JWTAuthorizationFilter(authenticationManager()))
                // 不需要session
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling().authenticationEntryPoint(new JWTAuthenticationEntryPoint())
                .accessDeniedHandler(new JWTAccessDeniedHandler());      //添加无权限时的处理
        http.addFilterBefore(myFilterSecurityInterceptor, FilterSecurityInterceptor.class);

    }

//    @Bean
//    CorsConfigurationSource corsConfigurationSource() {
//        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
//        return source;
//    }
}