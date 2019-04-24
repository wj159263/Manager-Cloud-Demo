package com.wj.manager.service;

import com.wj.manager.common.entity.SysUser;

import java.util.List;

public interface SysUserService {
    public  List<SysUser> getUserListByAccount(String username);
}
