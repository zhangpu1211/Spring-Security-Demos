package org.zp.secret.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.zp.secret.service.UserService;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    MyAuthenctiationSuccessHandler myAuthenctiationSuccessHandler;
    @Autowired
    MyAuthFailHandler myAuthFailHandler;
    @Autowired
    UserService userService;
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("admin").password("$2a$10$2cPRItUHyE1GSZnrYWHiQevpbxn4ikWgOa1PYL5miWvqK8GFVCWb6").roles("admin")
//        .and().withUser("java").password("$2a$10$rygGQylvmoAFmPcKQP6xvepNVAw9Bxp0sbAphxKQwhAV79Au0ECvq").roles("user");
        auth.userDetailsService(userService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin/**").hasRole("admin")
                .antMatchers("/user/**").hasAnyRole("admin","user")
                .anyRequest().authenticated()  //其他路径认证之后就可以访问
                .and()
                .formLogin()
                .loginProcessingUrl("/doLogin")  //处理登录请求的地址
                .successHandler(myAuthenctiationSuccessHandler)
                .failureHandler(myAuthFailHandler)
                .permitAll()
                .and()
                .csrf().disable();
    }
}
