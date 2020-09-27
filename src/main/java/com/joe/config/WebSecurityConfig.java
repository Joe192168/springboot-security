package com.joe.config;

import com.joe.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

@Configuration
//@EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyFilterSecurityInterceptor myFilterSecurityInterceptor;

    //定义用户信息服务（查询用户信息）
    @Bean
    public UserDetailsService userDetailsService(){
        /*InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("zhangsan").password("123").authorities("p1").build());
        manager.createUser(User.withUsername("lisi").password("456").authorities("p2").build());
        return manager;*/

        return new UserDetailsServiceImpl();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()); //user Details Service验证

    }

    //密码编码器
    @Bean
    public PasswordEncoder passwordEncoder(){
        //return NoOpPasswordEncoder.getInstance(); 明文
        return new LoginPasswordEncoder();
    }

    //安全拦截机制（最重要）
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //.antMatchers("/r/r1").hasAuthority("p1")
                //.antMatchers("/r/r2").hasAuthority("p2")
                .antMatchers("/r/**").authenticated()//所有/r/**的请求必须认证通过
                .and()
                .csrf().disable() //禁用csrf
                .headers().frameOptions().disable() //禁用frame options
                .and()
                .formLogin()//允许表单登录
                .loginPage("/user/login")
                .loginProcessingUrl("/j_spring_security_check").failureUrl("/user/login?error=true").defaultSuccessUrl("/main/main")
                .successForwardUrl("/login-success")//自定义登录成功的页面地址
                .and().logout().permitAll();//除了/r/**，其它的请求可以访问
        http.addFilterBefore(myFilterSecurityInterceptor, FilterSecurityInterceptor.class);

    }
}