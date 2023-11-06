package com.bear.filter;

import com.bear.model.User;
import com.bear.model.UserDetailModel.LoginUser;
import com.bear.util.JwtUtil;
import com.sun.xml.internal.bind.v2.TODO;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

//
//    @Autowired
//    private RedisCache redisCache;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //获取token
        String token = request.getHeader("token");
        if (!StringUtils.hasText(token)) {
            //放行
            filterChain.doFilter(request, response);
            return;
        }
        //解析token
        String userid;
        try {
            Claims claims = JwtUtil.parseJWT(token);
            userid = claims.getSubject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("token非法");
        }

        // todo 用户信息和权限从redis中获取，用户信息放入loginUser，再将权限放入authenticationToken中的authorities


//        String redisKey = "login:" + userid;
//        LoginUser   = redisCache.getCacheObject(redisKey);
//        if(Objects.isNull(loginUser)){
//            throw new RuntimeException("用户未登录");
//        }
        //存入SecurityContextHolder
        // 从redis中获取到了数据
        LoginUser loginUser = new LoginUser();
        User user = new User();
        user.setUserName("jarry");
        user.setPassword("123456");
        loginUser.setUser(user);


        // todo 这边封装的权限应该从redis中获取
        List<String> objects = new ArrayList<>();
        objects.add("hello");
//        objects.add("ROLE_hello");    // 使用hasAnyRole的时候，角色前面要加上ROLE_
//        loginUser.setPermissions(objects);
        List<GrantedAuthority> grantedAuthorityList = objects.stream().
                map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginUser,null,grantedAuthorityList);


        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        //放行
        filterChain.doFilter(request, response);
    }
}