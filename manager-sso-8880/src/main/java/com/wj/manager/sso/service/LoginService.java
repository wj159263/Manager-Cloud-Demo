package com.wj.manager.sso.service;

import com.wj.manager.common.entity.SysUser;

public interface LoginService {

    public SysUser login(String account, String password);
    public String get(String key);
}
