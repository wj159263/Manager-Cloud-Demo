package com.wj.manager.service;

import com.wj.manager.common.entity.SysUser;
import com.wj.manager.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    SysUserMapper sysUserMapper;
    @Override
    public List<SysUser> getUserListByAccount(String username) {
        Example example = new Example(SysUser.class);
        //第一个参数是entity的字段
        example.createCriteria().andEqualTo("account",username);
        List<SysUser> sysUsers = sysUserMapper.selectByExample(example);
        return sysUsers;
    }
}
