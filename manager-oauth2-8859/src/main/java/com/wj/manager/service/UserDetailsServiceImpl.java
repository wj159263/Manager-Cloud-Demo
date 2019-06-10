/*
package com.wj.manager.service;

import com.wj.manager.common.entity.SysUser;
import com.wj.manager.dto.JwtUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    SysUserService userService;
    @Autowired
    PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (StringUtils.isEmpty(username)) {
            return null;
        }
        List<SysUser> users = userService.getUserListByAccount(username);
        if(users == null || users.size() < 1){
            return null;
        }
        SysUser sysUser = users.get(0);
        return sysUser2JwtUser(sysUser);
    }

    private JwtUser sysUser2JwtUser(SysUser sysUser){
        if(sysUser == null){
            return null;
        }
        Set<String> menus = new HashSet<>();
        menus.add("/user");
        menus.add("/user/add");
        menus.add("/user/edit");
        String permissions = StringUtils.join(menus.toArray(), ",");
        Integer status = sysUser.getStatus();

        JwtUser jwtUser = new JwtUser(sysUser.getAccount(),sysUser.getPassword(),status ==1 ,
                true ,true, status != 2,
                AuthorityUtils.commaSeparatedStringToAuthorityList(permissions));

        return jwtUser;
    }


}
*/
