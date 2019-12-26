package org.nuaa.wa.waelder.client;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.google.gson.Gson;
import org.nuaa.wa.waelder.entity.SysMsgEntity;
import org.nuaa.wa.waelder.service.SysMsgService;
import org.nuaa.wa.waelder.util.constant.LogLevel;
import org.nuaa.wa.waelder.util.constant.StatusConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Value("${aliyun.sms.ver-alarm-msg-template}")
    private String verAlarmMsgTemplate;

    @Value("${aliyun.sms.sign-name}")
    private String signName;

    @Autowired
    private SysMsgService sysMsgService;

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

        SysMsgEntity sysMsgEntity = new SysMsgEntity();
        sysMsgEntity.setPhone(phone);
        sysMsgEntity.setTemplateName(verCodeTemplate);
        sysMsgEntity.setContent("code : " + code);
        try {
            CommonResponse response = client.getCommonResponse(request);
            logger.info(gson.toJson(response));
            sysMsgEntity.setStatus(response.getHttpResponse().getStatus());
        } catch (ClientException e) {
            logger.warn("send sms code fail, phone = {}, code = {}, err : {}", phone, code, gson.toJson(e));
            sysMsgEntity.setStatus(StatusConstant.MSG_FAIL_STATUS);
        }
        sysMsgService.addSysMsg(sysMsgEntity);
        return 0;
    }

    @Override
    public int sendAlarmMsg(String account, String alarmId, String deviceName) {
        SysMsgEntity sysMsgEntity = new SysMsgEntity();
        sysMsgEntity.setPhone(account);
        sysMsgEntity.setTemplateName(verCodeTemplate);
        sysMsgEntity.setContent("msg : " + alarmId.concat(" ").concat(deviceName));
        DefaultProfile profile = DefaultProfile.getProfile(
                "cn-hangzhou", accessKeyId, accessKeySecret
        );
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("PhoneNumbers", account);
        request.putQueryParameter("SignName", signName);
        request.putQueryParameter("TemplateCode", verAlarmMsgTemplate);
        request.putQueryParameter("TemplateParam", "{device: ".concat(deviceName).concat(", alarmId: ").concat(alarmId).concat("}"));

        try {
            CommonResponse response = client.getCommonResponse(request);
            logger.info(gson.toJson(response));
            sysMsgEntity.setStatus(response.getHttpResponse().getStatus());
        } catch (ClientException e) {
            logger.warn("send sms code fail, phone = {}, err : {}", account, gson.toJson(e));
            sysMsgEntity.setStatus(StatusConstant.MSG_FAIL_STATUS);
        }
        sysMsgService.addSysMsg(sysMsgEntity);
        return 0;
    }


}
