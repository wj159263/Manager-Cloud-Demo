package com.wj.manager.service.impl;

import cn.hutool.http.HttpUtil;
import com.github.wxpay.sdk.WXPayUtil;
import com.wj.manager.service.PayService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
//具体看https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=9_1
@Service
public class WeChatPayServiceImpl implements PayService {
    //@Value("${vxpay.appid}")
    private final static String APPID = "wx8397f8696b538317";
    private final static String MCH_ID = "1473426802";

    /**
     * 告诉微信要支付，微信会给出一个url，然后用url在前端生成二维码
     * @param out_trade_no
     * @param total_fee
     * @return
     */
    @Override
    public Map createNative(String out_trade_no, String total_fee) {


        //1、参数封装
        Map<String,String> params = new HashMap<String,String>();
        params.put("appid",APPID);
        params.put("mch_id",MCH_ID);
        params.put("nonce_str", WXPayUtil.generateNonceStr());
        //params.put("sign","");
        params.put("body","腾讯充值中心-QQ会员充值1");
        params.put("out_trade_no",out_trade_no);
        params.put("total_fee",total_fee);//金额，单位为分
        params.put("spbill_create_ip","127.0.0.1");//ip，不产生影响
        params.put("notify_url","http://baidu.com");//支付成功后的回调地址，模式二可随便填写
        params.put("trade_type","NATIVE");
        //2、发送请求
        String xmlParam = null;
        String partner_key = "8A627A4578ACE384017C997F12D68B23";
        try {
            xmlParam = WXPayUtil.generateSignedXml(params, partner_key);
            //3、获取结果
            String vxResult = HttpUtil.post("https://api.mch.weixin.qq.com/pay/unifiedorder", xmlParam);
            System.out.println(vxResult);
            Map<String, String> resultMap = null;
            //vxResult要看具体得到的数据，然后进行修改，转成map。返回结果{"return_code":"SUCCESS"}
            resultMap = WXPayUtil.xmlToMap(vxResult);
            Map<String, String> dataMap = null;
            if (resultMap != null && "SUCCESS".equals((String) resultMap.get("return_code ")) &&
                    "SUCCESS".equals((String) resultMap.get("result_code "))
                    ) {
                dataMap = new HashMap<>();
                //微信返回的数据包含很多数据，包括商家信息，不应该传到前端
                dataMap.put("code_url", resultMap.get("code_url"));
                dataMap.put("out_trade_no", out_trade_no);
                dataMap.put("total_fee", total_fee);
            }
            return dataMap;
        } catch (Exception e) {
            e.printStackTrace();
            return new HashMap<>();
        }

    }

    /**
     * 查询订单状态，上面的方法有个out_trade_no，所以可根据out_trade_no追踪
     * @param out_trade_no
     * @return
     */
    @Override
    public Map queryPayStatus(String out_trade_no) {
        Map map = new HashMap();
        map.put("appid","wx8397f8696b538317");
        map.put("mch_id","1473426802");
        map.put("out_trade_no",out_trade_no);
        map.put("nonce_str",WXPayUtil.generateNonceStr());//随机字符串
        String partner_key = "8A627A4578ACE384017C997F12D68B23";
        try {
            String xmlParam = WXPayUtil.generateSignedXml(map, partner_key);
           //System.out.println("xmlParam=="+xmlParam);
            String vxResult = HttpUtil.post("https://api.mch.weixin.qq.com/pay/orderquery", xmlParam);
           // System.out.println("vxResult=="+vxResult);
            Map<String, String> resultMap = null;
            //vxResult要看具体得到的数据，然后进行修改，转成map。返回结果{"return_code":"SUCCESS"}
            resultMap = WXPayUtil.xmlToMap(vxResult);
            //System.out.println("!11");
            return resultMap;


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map doCanclePay(String out_trade_no){
        Map map = new HashMap();
        map.put("appid","wx8397f8696b538317");
        map.put("mch_id","1473426802");
        map.put("out_trade_no",out_trade_no);
        map.put("nonce_str",WXPayUtil.generateNonceStr());//随机字符串
        String partner_key = "8A627A4578ACE384017C997F12D68B23";
        String cancelPayUrl="https://api.mch.weixin.qq.com/pay/closeorder";
        try {
            String xmlParam = WXPayUtil.generateSignedXml(map, partner_key);
            String vxResult = HttpUtil.post(cancelPayUrl, xmlParam);
            Map<String, String> resultMap = null;
            //vxResult要看具体得到的数据，然后进行修改，转成map。返回结果{"return_code":"SUCCESS"}
            resultMap = WXPayUtil.xmlToMap(vxResult);
            return resultMap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    //取消支付
    public Map cancelPay(String out_trade_no) {
        Map<String,String> cancleResult = doCanclePay(out_trade_no);
        //取消支付前先查询下支付状态
        if(cancleResult != null && "FAIL".equals(cancleResult.get("return_code"))){
            //返回结果为已支付   orderPaid
            if("ORDERPAID".equals(cancleResult.get("err_code"))){
                //执行支付成功的逻辑
                //修改订单状态，已支付，待支付等等
                int hasPay =1;
                //orderService.updataOrderByTradeNo(out_trade_no,hasPay);
                Map<String,String> result = new HashMap<>();
                result.put("msg","支付成功");
                return result;
            }
        }

        //向微信提出取消支付成功
        if(cancleResult != null && "SUCCESS".equals(cancleResult.get("result_code"))){
            //1、删除订单等操作

            //2、回滚商品库存等等

            //===========分割============
            Map<String,String> result = new HashMap<>();
            result.put("msg","取消支付成功");
            return result;
        }
        return null;
    }
}
