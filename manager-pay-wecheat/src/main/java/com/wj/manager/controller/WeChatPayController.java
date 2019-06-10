package com.wj.manager.controller;

import com.wj.manager.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
//具体看https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=9_1

/**
 * 先通过/wxpay/native获得支付url，在前端通过获得的url生成二维码。
 */
@Controller
public class WeChatPayController {
    @Autowired
    private PayService payService;
    AtomicInteger ai = new AtomicInteger();
    @GetMapping("/vxpay/native")
    //@ResponseBody
    public Object createNative(/*String out_trade_no, String total_fee*/){
        String out_trade_no = "1231131";
        String total_fee = "1";
        Map aNative = payService.createNative(out_trade_no, total_fee);
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        request.setAttribute("data",aNative);
        return "pay";
    }

    @GetMapping("/wxpay/native")
    @ResponseBody
    public Object createNative1(/*String out_trade_no, String total_fee*/){
        String out_trade_no = "1231131";
        String total_fee = "1";
        Map aNative = payService.createNative(out_trade_no, total_fee);
        return aNative;
    }
    @GetMapping("/wxpay/query")
    @ResponseBody
    public Object queryPayStatus(/*String out_trade_no*/) throws InterruptedException {
        String out_trade_no = "1231131";
        Map result = null;
        Long begin = System.currentTimeMillis();
       // BaseReqult baseReqult =null;
        while (true){
            //注意微信会返回return_code和trade_state，前者是返回状态码，无论支付成功还是失败都为SUCCESS
            //trade_state才是标识交易状态的
            if (result.get("trade_state") != null && "SUCCESS".equals(result.get("trade_state"))){
                // baseReqult = BaseReqult.ok("支付成功");
                result = new HashMap<>();
                result.put("msg","支付成功");
                break;
            }
            System.out.println("循环次数=======:::::"+ai.incrementAndGet());
            Long now = System.currentTimeMillis();
            if ((now - begin)> 5000){
                //超时，重新生成二维码等待支付
                // baseReqult = BaseReqult.fail("支付失败");
                result = new HashMap<>();
                result.put("msg","支付超时，请刷新页面重试!");


               // =====================分割=====================

                //超时清除订单，比如秒时。必须得在5分钟后才能取消
                payService.cancelPay(out_trade_no);


                break;
            }
            result = payService.queryPayStatus(out_trade_no);
            if(result == null){
               // baseReqult = BaseReqult.fail("支付失败");
                result = new HashMap<>();
                result.put("msg","支付失败");
                break;
            }

            if("FAIL11".equals(result.get("return_code"))){
                // baseReqult = BaseReqult.fail("支付失败");
                result = new HashMap<>();
                result.put("msg","支付失败");
                break;
            }
            Thread.sleep(3000);//不能调用太频繁，停歇一会
        }
       //return baseReqult;
        return result;
    }

}
