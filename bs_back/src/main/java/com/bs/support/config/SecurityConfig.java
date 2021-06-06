package com.bs.support.config;


import com.bs.support.compenment.JwtAuthenticationTokenFilter;
import com.bs.support.compenment.RestAuthenticationEntryPoint;
import com.bs.support.compenment.RestfulAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;
    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;
    @Autowired
    private RestfulAccessDeniedHandler restfulAccessDeniedHandler;

    /**
     * 配置Swagger2不被SpringSecurity框架拦截
     *
     * @author zhangqianchun
     * @param web
     */
    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers(
                "/swagger-ui.html",
                // swagger api json
                "/v2/api-docs",
                // 用来获取支持的动作
                "/swagger-resources/configuration/ui",
                // 用来获取api-docs的URI
                "/swagger-resources",
                // 安全选项
                "/swagger-resources/configuration/security",
                "/swagger-resources/**",
                //补充路径，通过浏览器控制台发现该/webjars路径下的文件被拦截，加上此过滤条件即可。
                "/webjars/**",
                //添加swagger不需要被security拦截的接口，我这里添加的是全部接口
                "/**"
        );
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http .cors().and()
                .csrf().disable()
                .sessionManagement()// 基于token，所以不需要 securityContext
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/css/**", "/js/**", "/fonts/**","/login/in","/login/register","/code/imgCode").permitAll() //都可以访问
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .anyRequest().authenticated() // 任何请求都需要认证
                .and()
                .userDetailsService(userDetailsService)
        ; //自定义 登录界面
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

        //添加自定义未授权和未登录结果返回
        http.exceptionHandling()
                .accessDeniedHandler(restfulAccessDeniedHandler)
                .authenticationEntryPoint(restAuthenticationEntryPoint);
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
