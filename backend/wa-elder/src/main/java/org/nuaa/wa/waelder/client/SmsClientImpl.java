package org.nuaa.wa.waelder.client;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.google.gson.Gson;
import org.nuaa.wa.waelder.util.constant.LogLevel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Name: SmsClientImpl
 * @Description: TODO
 * @Author: tomax
 * @Date: 2019-10-28 23:20
 * @Version: 1.0
 */
@Component("sms")
public class SmsClientImpl implements SmsClient{
    private static Logger logger = LoggerFactory.getLogger(LogLevel.SERVICE);
    private static Gson gson = new Gson();

    @Value("${aliyun.access-key-id}")
    private String accessKeyId;

    @Value("${aliyun.access-key-secret}")
    private String accessKeySecret;

    @Value("${aliyun.sms.ver-code-template}")
    private String verCodeTemplate;

    @Value("${aliyun.sms.sign-name}")
    private String signName;

    @Override
    public int sendVerifyCode(String phone, String code) {
        DefaultProfile profile = DefaultProfile.getProfile(
                "cn-hangzhou", accessKeyId, accessKeySecret
        );
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", signName);
        request.putQueryParameter("TemplateCode", verCodeTemplate);
        request.putQueryParameter("TemplateParam", "{code:" + code + "}");

        try {
            CommonResponse response = client.getCommonResponse(request);
            logger.info(gson.toJson(response));
        } catch (ClientException e) {
            logger.warn("send sms code fail, phone = {}, code = {}, err : {}", phone, code, gson.toJson(e));
        }

        return 0;
    }

    @Override
    public int sendAlarmMsg(String account, String msg) {
        return 0;
    }


}
