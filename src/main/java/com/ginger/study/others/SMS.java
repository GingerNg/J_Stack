package com.ginger.study.others;
import java.io.IOException;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.FormatType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.aliyuncs.sms.model.v20160927.SingleSendSmsRequest;
import com.aliyuncs.sms.model.v20160927.SingleSendSmsResponse;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

public class SMS {
    /**
     *
     * @return
     */
//    public String getAcsResponse(String toPhone, String code) {
//        SingleSendSmsResponse httpResponse = new SingleSendSmsResponse();
//        String result = "";
//        try {
//            IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou",accessKey, accessSecret);
//            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", "Sms","sms.aliyuncs.com");
//            IAcsClient client = new DefaultAcsClient(profile);
//            SingleSendSmsRequest request = new SingleSendSmsRequest();
//            request.setSignName(sign);// 控制台创建的签名名称
//            request.setTemplateCode(templateCode);// 控制台创建的模板CODE
//
//            JSONObject jsonCode = new JSONObject();
//            jsonCode.accumulate("code", code);
//            request.setParamString(jsonCode.toString());
//            request.setRecNum(toPhone);
//            request.setAcceptFormat(FormatType.JSON); // 格式为json
//            httpResponse = client.getAcsResponse(request);
//            logger.info("send msgcode response: {} ", httpResponse.toString());
//            result = ConstUtils.ERROR_0;
//        } catch (ClientException e) {
//            e.printStackTrace();// //InvalidSignName.Malformed : The specified// sign name is wrongly formed.
//            logger.error("send msgcode error :{}", e.getMessage());
//            result = e.getErrCode();
//        }
//        return result;
//    }
    public static void main(String[] args) throws HttpException, IOException {
        //http://blog.csdn.net/u012811805/article/details/59153684
        HttpClient client = new HttpClient();
        PostMethod post = new PostMethod("http://gbk.sms.webchinese.cn");
        post.addRequestHeader("Content-Type",
                "application/x-www-form-urlencoded;charset=gbk");
        NameValuePair[] data = { new NameValuePair("Uid", "你的用户名"),
                new NameValuePair("Key", "api秘钥"),
                new NameValuePair("smsMob", "手机号"),
                new NameValuePair("smsText", "这是专用于测试的信息，能否正常发短信呢？") };// 短信内容
        post.setRequestBody(data);

        client.executeMethod(post);
        Header[] headers = post.getResponseHeaders();
        int statusCode = post.getStatusCode();
        System.out.println("statusCode:" + statusCode);
        for (Header h : headers) {
            System.out.println("---" + h.toString());
        }
        String result = new String(post.getResponseBodyAsString().getBytes(
                "gbk"));
        System.out.println(result);

    }
}
