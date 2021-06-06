package com.bs.support.compenment;

import com.bs.support.util.JwtTokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * JWT登录授权过滤器
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationTokenFilter.class);

    //注入值是MyUserDetailService
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Value("${jwt.tokenHeader}")//Authorization
    private String tokenHeader;

    @Value("${jwt.tokenHead}")
    private String tokenHead;//bearer

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {
        //从 header  中获取 Authorization
        String authHeader = request.getHeader(this.tokenHeader);
        // 判断 authHeader  不为空  并且以 bearer 开头
        if (authHeader != null) {
            boolean b1 = StringUtils.startsWithIgnoreCase(authHeader,this.tokenHead);
            if (b1) {
                //截取 bearer 后面的字符串  并且 两端去空格（获取token）
                String authToken = authHeader.substring(this.tokenHead.length()).trim();// The part after "Bearer "

                String username = jwtTokenUtil.getUserNameFromToken(authToken);
                LOGGER.info("checking username:{}", username);
                // 用户名不为空  并且SecurityContextHolder.getContext()  存储 权限的容器中没有相关权限则继续
                boolean b = SecurityContextHolder.getContext().getAuthentication() == null;
                if (username != null && b) {
                    //从数据库读取用户信息
                    UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
                    //校验token
                    if (jwtTokenUtil.validateToken(authToken, userDetails)) {
                        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                        WebAuthenticationDetails details = new WebAuthenticationDetailsSource().buildDetails(request);
                        //设置用户ip
                        authentication.setDetails(details);
                        LOGGER.info("authenticated user:{}", username);
                        //存入本线程的安全容器   在访问接口拿到返回值后 要去主动清除 权限，避免干扰其他的线程
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                }
            }
        }
        chain.doFilter(request, response);
    }
}
