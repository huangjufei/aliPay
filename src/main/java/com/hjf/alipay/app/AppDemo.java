package com.hjf.alipay.app;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;

public class AppDemo {


    public static void main(String[] args) {

        String APP_ID = "2016102200735699";
        String APP_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDUwpk4UOPZ+f0AoU/EcXIQjr1MN2rLhG6EE9AMXK8ypRFsJKpwicfzI9rba/iwc9W8TvGw7jiANxr2zu9pjztHfojVTKiTOq3nkfz4FKYNAMjK14UGFO+YM4igGsaTSf64AB9SvMT0Lq2xZB+yTDw/sWWr9xqW9Y6F4myYSTtfwxlX3yF5phfM0mG+oJtKPY0/OOFZbE1Od0mGmUYB9umh8iPvANoNats8CXp6WZTiZfuWRfNp1qzT/s/ju5EgJVrHvjdxnsTnpoqfUqLs2/J5olBcAACO5NZ0Ex8UNnJABlzUDexP0Qt6Cpem9bPK7B0JcCCP8xuxycCKLQI7tIgfAgMBAAECggEAaVpDDMPhT9qeEWXPXfLfDJvh2I/IbiaRow08hCKRDn72dZpiW3+eZyNmCCka6CUzvR9KRtZvW+3tiDFIE/k83VYm45gmEWGJI5YCscVnxCCTTP4TfMN5MOCz0rPviJenbe2BlTKsRE2o7KIrn9poKxHoypJ9tCq7/Ef2hiIdKHlIOn9tQbow0qCePny/7h0LY6HEmQAOa7MlWSC70aXM1gqWIdA+kqswyydMBo1aE7VkA/bi4LvSMdFqWreOjs71l8mYB+VLCFOHk2xtkzVeHgAV0ofXXGhq1QSk9AT4zrShPs9e9VlHQDrx+HeezhsCKdRjBwJlGeTa6cETf1VEwQKBgQDr8zeHc1fxZ/nHHo6LcPYtqC0B6I5lDU3M0CzOcMhuSuV+A3vtiOltoxN3O5KJghLFbmHF6T6TTrZzALaR7VgOIGWpcCqzj7HIoN/6irPw5gzlwgA9CKSvAKp8lgiakAoTcZYhUOo4Wfhk4vp0t5/MjtK85Ry34aZLUbxsK5k3iQKBgQDm1upfWyi87tptYitBNWjfSPC7+8uUKg1NCCoZ1QKyRv4p0FUNkELsXTpdojnNbUQ5tfUZmDT/AIltKvK/mQ61VEUWTvjLJ2/aE//a98N2tgYI+8XQrjtM/TXhP8fyesYLkv5bqp5G0q7A2/M8wiBLRBGg60KWtTUPDwJTWpmwZwKBgQCS26xJU2J+cCAIbp18bw/a9R0mXfAbN3+gJkZtK8ek1lsm5tZAz4Gy6tiX0u1vRxWV6xFSFM4M8+YdiGkaROcUy9da8eSyJJMcc/1UvPYoz5dhfjGVy4ZtInX624t5YIPU/FLcY7eU4XL4oEoRUaVVteO3DMemL/3ITZSAo1ZXOQKBgFSJxqGPX/b5PnMqrY6fAb4sF0Ww7Bhh4icg41I8iVVC3JfxnQRvnaK0bciWvbZ22rKL6awq6tWsm6eoP+hdJF7R8s89sq2rzrG9+0h0zleuR72IfyRyenMylEXettodjmP0vERT0s07IRauNoE80tjdn9sf7W4GBN1z8LUYDArnAoGAdk7gg4iG+eBNpaOsVQ7FPLiSUjY0MhHebm+utR0U8Tm0OqgzoGGDVw8zfjPLV7c0UEXZ55jtsj1F1ViJEKCp4b+olalG/9EW9Lnd33lHwH23Nr6zleq7CS6P9lB/dXPmCftcl1v7CaEU8hV4vAfWR8vupT/wCmhkz8aqrnOdFgQ=";
        String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAlggoTY8cEqvfB0MpFuDVUfhvliEMdwWiJmuhodEccnbbQuF6tB7tz4R62pm6pnHvHiNy/mxNd3BdPCemC45MtZgr4M1T6MYPhg513jYPfjlKe6TmEsovAfSogZOWf9T1tdkA3EuD9zjlbmZzAT1QnRgQN9xSycD3pXPBe0DBzJ1sMl+phBv06uEo+sPBIlbluavxfo6o0qF+oOTjxhjQyYhAwgS5UqoBK/bZhruR8heMlYx5FWKbgV3eFOjv9GwG72UmjIrG1DK4bMt6i2d2EUCbDuit8gDNR1gK61SRSXaY52Lm8vaeoCYYChwQc+JDi8AsSsrxFNu7lACGR8Yf/wIDAQAB";
        String CHARSET = "utf-8";

        //实例化客户端
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipaydev.com/gateway.do",
                APP_ID, APP_PRIVATE_KEY, "json",
                CHARSET, ALIPAY_PUBLIC_KEY, "RSA2");

//实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
//SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        model.setBody("我是测试数据");
        model.setSubject("App支付测试Java");
        model.setOutTradeNo("123123ds");
        model.setTimeoutExpress("30m");
        model.setTotalAmount("0.01");
        model.setProductCode("QUICK_MSECURITY_PAY");
        request.setBizModel(model);
        request.setNotifyUrl("商户外网可以访问的异步地址");
        try {
            //这里和普通的接口调用不同，使用的是sdkExecute
            AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
            System.out.println(response.getBody());//就是orderString 可以直接给客户端请求，无需再做处理。
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }

    }
}
