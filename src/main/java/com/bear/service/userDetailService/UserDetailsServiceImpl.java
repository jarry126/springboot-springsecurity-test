package com.bear.service.userDetailService;

import com.bear.model.User;
import com.bear.model.UserDetailModel.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @Author 三更  B站： https://space.bilibili.com/663528522
 *  获取到传过来的用户名，从数据库中查询数据，封装数据
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        //根据用户名查询用户信息
//        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
//        wrapper.eq(User::getUserName,username);
//        User user = userMapper.selectOne(wrapper);
        // todo 假设从数据库中查询到了这一条数据，使用用户名
        // todo 使用BCryptPasswordEncoder加密， 密码$2a$10$UZKtX2iQQwr6LHiywe6nquFczaxzXHgIM5VUCDfEwYDDO.Vla5vgq
        User user = new User();
        user
                .setId(1L)
                .setUserName(username)
                .setNickName("测试使用")
                .setPassword("$2a$10$UZKtX2iQQwr6LHiywe6nquFczaxzXHgIM5VUCDfEwYDDO.Vla5vgq")
                .setStatus("1")
                .setEmail("1223234@qq.com")
                .setPhonenumber("1212121212");
        //如果查询不到数据就通过抛出异常来给出提示
        if(Objects.isNull(user)){
            throw new RuntimeException("用户名或密码错误");
        }
        //TODO 根据用户查询权限信息 添加到LoginUser中
        
        //封装成UserDetails对象返回 
        return new LoginUser(user);
    }
}