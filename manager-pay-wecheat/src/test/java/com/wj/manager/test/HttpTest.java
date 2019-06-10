package com.wj.manager.test;

import com.wj.manager.service.impl.WeChatPayServiceImpl;
import org.junit.Test;

public class HttpTest {
    @Test
    public void aa(){
        WeChatPayServiceImpl service = new WeChatPayServiceImpl();
        service.createNative("aa","aa");
    }
}
