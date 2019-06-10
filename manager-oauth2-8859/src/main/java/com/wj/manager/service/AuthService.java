package com.wj.manager.service;

import com.wj.manager.dto.AuthToken;

public interface AuthService {
    public AuthToken login(String username, String password);
    public Integer logout(Integer userid);

    public boolean unlock(Integer userId, String password);

    public void testTran();

    public void testTran2();

    public void testProgramTran();
    public void testProgramTran2();
    public void testAlibabaGrobalTransaction();
}
