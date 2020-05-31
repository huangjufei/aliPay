package com.hjf.alipay.appweb;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.response.AlipayTradeQueryResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 这个代码使用的沙箱账号支付成功的;
 * <p>
 * 需要 natapp 软件,来支持外网访问,修改下面domain域名
 */
@Controller
public class appWeb {

    private static final String APP_ID = "2016102200735699";
    private static final String APP_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDUwpk4UOPZ+f0AoU/EcXIQjr1MN2rLhG6EE9AMXK8ypRFsJKpwicfzI9rba/iwc9W8TvGw7jiANxr2zu9pjztHfojVTKiTOq3nkfz4FKYNAMjK14UGFO+YM4igGsaTSf64AB9SvMT0Lq2xZB+yTDw/sWWr9xqW9Y6F4myYSTtfwxlX3yF5phfM0mG+oJtKPY0/OOFZbE1Od0mGmUYB9umh8iPvANoNats8CXp6WZTiZfuWRfNp1qzT/s/ju5EgJVrHvjdxnsTnpoqfUqLs2/J5olBcAACO5NZ0Ex8UNnJABlzUDexP0Qt6Cpem9bPK7B0JcCCP8xuxycCKLQI7tIgfAgMBAAECggEAaVpDDMPhT9qeEWXPXfLfDJvh2I/IbiaRow08hCKRDn72dZpiW3+eZyNmCCka6CUzvR9KRtZvW+3tiDFIE/k83VYm45gmEWGJI5YCscVnxCCTTP4TfMN5MOCz0rPviJenbe2BlTKsRE2o7KIrn9poKxHoypJ9tCq7/Ef2hiIdKHlIOn9tQbow0qCePny/7h0LY6HEmQAOa7MlWSC70aXM1gqWIdA+kqswyydMBo1aE7VkA/bi4LvSMdFqWreOjs71l8mYB+VLCFOHk2xtkzVeHgAV0ofXXGhq1QSk9AT4zrShPs9e9VlHQDrx+HeezhsCKdRjBwJlGeTa6cETf1VEwQKBgQDr8zeHc1fxZ/nHHo6LcPYtqC0B6I5lDU3M0CzOcMhuSuV+A3vtiOltoxN3O5KJghLFbmHF6T6TTrZzALaR7VgOIGWpcCqzj7HIoN/6irPw5gzlwgA9CKSvAKp8lgiakAoTcZYhUOo4Wfhk4vp0t5/MjtK85Ry34aZLUbxsK5k3iQKBgQDm1upfWyi87tptYitBNWjfSPC7+8uUKg1NCCoZ1QKyRv4p0FUNkELsXTpdojnNbUQ5tfUZmDT/AIltKvK/mQ61VEUWTvjLJ2/aE//a98N2tgYI+8XQrjtM/TXhP8fyesYLkv5bqp5G0q7A2/M8wiBLRBGg60KWtTUPDwJTWpmwZwKBgQCS26xJU2J+cCAIbp18bw/a9R0mXfAbN3+gJkZtK8ek1lsm5tZAz4Gy6tiX0u1vRxWV6xFSFM4M8+YdiGkaROcUy9da8eSyJJMcc/1UvPYoz5dhfjGVy4ZtInX624t5YIPU/FLcY7eU4XL4oEoRUaVVteO3DMemL/3ITZSAo1ZXOQKBgFSJxqGPX/b5PnMqrY6fAb4sF0Ww7Bhh4icg41I8iVVC3JfxnQRvnaK0bciWvbZ22rKL6awq6tWsm6eoP+hdJF7R8s89sq2rzrG9+0h0zleuR72IfyRyenMylEXettodjmP0vERT0s07IRauNoE80tjdn9sf7W4GBN1z8LUYDArnAoGAdk7gg4iG+eBNpaOsVQ7FPLiSUjY0MhHebm+utR0U8Tm0OqgzoGGDVw8zfjPLV7c0UEXZ55jtsj1F1ViJEKCp4b+olalG/9EW9Lnd33lHwH23Nr6zleq7CS6P9lB/dXPmCftcl1v7CaEU8hV4vAfWR8vupT/wCmhkz8aqrnOdFgQ=";
    private static final String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAlggoTY8cEqvfB0MpFuDVUfhvliEMdwWiJmuhodEccnbbQuF6tB7tz4R62pm6pnHvHiNy/mxNd3BdPCemC45MtZgr4M1T6MYPhg513jYPfjlKe6TmEsovAfSogZOWf9T1tdkA3EuD9zjlbmZzAT1QnRgQN9xSycD3pXPBe0DBzJ1sMl+phBv06uEo+sPBIlbluavxfo6o0qF+oOTjxhjQyYhAwgS5UqoBK/bZhruR8heMlYx5FWKbgV3eFOjv9GwG72UmjIrG1DK4bMt6i2d2EUCbDuit8gDNR1gK61SRSXaY52Lm8vaeoCYYChwQc+JDi8AsSsrxFNu7lACGR8Yf/wIDAQAB";
    private static final String domain = "http://m8ptxf.natappfree.cc";
    private static final String CHARSET = "utf-8";

    private static final String GET_WAY = "https://openapi.alipaydev.com/gateway.do";


    //------------上面是需要修该的参数-------------

    @GetMapping(value = "/appWebPay")
    public void doPost(HttpServletRequest httpRequest,
                       HttpServletResponse httpResponse) throws ServletException, IOException {


        AlipayClient alipayClient = new DefaultAlipayClient(GET_WAY,
                APP_ID, APP_PRIVATE_KEY, "json", CHARSET,
                ALIPAY_PUBLIC_KEY, "RSA2");

        /**
         * 这是支付接口,每个接口就是这个类不一样,请求的都是统一网关地址
         * 内面这个方法有实际调用地址:
         *  public String getApiMethodName() {
         *         return "alipay.trade.wap.pay";
         *   }
         */
        AlipayTradeWapPayRequest alipayRequest = new AlipayTradeWapPayRequest();//创建API对应的request

        alipayRequest.setReturnUrl(domain + "/return_url.jsp");
        alipayRequest.setNotifyUrl(domain + "/notify");//在公共参数中设置回跳和通知地址
        alipayRequest.setBizContent("{" +
                " \"out_trade_no\":\"20150320010101002\"," +
                " \"total_amount\":\"0.01\"," +
                " \"subject\":\"Iphone6 16G\"," +
                " \"product_code\":\"QUICK_WAP_PAY\"" +
                " }");//填充业务参数
        String form = "";
        try {
            form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        httpResponse.setContentType("text/html;charset=" + CHARSET);
        httpResponse.getWriter().write(form);//直接将完整的表单html输出到页面
        httpResponse.getWriter().flush();
        httpResponse.getWriter().close();
    }


    /**
     * 根据上面的out_trade_no 查询订单(沙箱模拟成功)
     * @param httpRequest
     * @param httpResponse
     * @throws AlipayApiException
     */
    @GetMapping(value = "/getByTradeNo")
    public void getByTradeNo(HttpServletRequest httpRequest,
                             HttpServletResponse httpResponse) throws AlipayApiException {

        AlipayClient alipayClient = new DefaultAlipayClient(GET_WAY,
                APP_ID, APP_PRIVATE_KEY, "json", CHARSET, ALIPAY_PUBLIC_KEY,
                "RSA2"); //获得初始化的AlipayClient
        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();//创建API对应的request类
        request.setBizContent("{" +" \"out_trade_no\":\"20150320010101002\" " + "}");//设置业务参数
        AlipayTradeQueryResponse response = alipayClient.execute(request);//通过alipayClient调用API，获得对应的response类
        System.out.print(response.getBody());
    }


    /**
     * 自定义回调接口,支付完成后阿里会回调这个接口
     */
    @PostMapping(value = "/notify")
    public void notifyUrl(HttpServletRequest httpRequest,
                          HttpServletResponse httpResponse) {
        System.out.println("回调接口被调");
    }

}
