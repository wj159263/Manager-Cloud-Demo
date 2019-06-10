package com.wj.manager.service;

import java.util.Map;

public interface PayService {
    public Map createNative(String out_trade_no, String total_fee);

    public Map queryPayStatus(String out_trade_no);

    public Map cancelPay(String out_trade_no);

}
