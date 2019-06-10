package com.wj.manager.provider.user.service;


import com.wj.manager.common.entity.SysUser;

public interface UserService {
    public Object insert();
    public void register(SysUser user);

    public SysUser login(String account, String password);
}
