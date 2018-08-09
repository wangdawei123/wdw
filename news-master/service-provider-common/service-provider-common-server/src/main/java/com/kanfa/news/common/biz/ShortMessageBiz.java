package com.kanfa.news.common.biz;

import com.kanfa.news.common.constant.UserEnum;
import com.kanfa.news.common.util.WinnerLookUtil;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.omg.CORBA.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class ShortMessageBiz {
    @Value("${winnerLook.requestUrl}")
    private String requestUrl;

    @Value("${winnerLook.userCode}")
    private String userCode;

    @Value("${winnerLook.userPass}")
    private String userPass;

    @Value("${winnerLook.channel}")
    private int channel;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    private static final int CAPTCHA_EXPIRES = 3 * 60;

    private static String USER_VALIDATE = "USER:VALIDATE:";

    public String sendValidateCode(String phone) {
        String randomCode = WinnerLookUtil.createRandomVcode();
        StringBuffer content = new StringBuffer("userPass=");
        content.append(userPass);
        content.append("&DesNo=");
        content.append(phone);
        content.append("&Msg=您的验证码是：");
        content.append(randomCode);
        content.append("，如非本人操作，请忽略此短信。【看法新闻】&Channel=");
        content.append(channel);
        String encryptStr = WinnerLookUtil.DESEncrypt(content.toString(), userPass);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("userCode", userCode));
        nvps.add(new BasicNameValuePair("submitInfo", encryptStr));

        String result = WinnerLookUtil.httpPost(requestUrl, nvps);

        org.dom4j.Document document = null;
        try {
            document = DocumentHelper.parseText(result);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        org.dom4j.Element rootElement = document.getRootElement();
        this.redisTemplate.opsForValue().set(USER_VALIDATE + rootElement.getStringValue(), randomCode, CAPTCHA_EXPIRES, TimeUnit.SECONDS);
        return rootElement.getStringValue();
    }

    public void checkSMSCode(String code, String sessionId) {
        String beforeSessionId = this.redisTemplate.opsForValue().get(USER_VALIDATE + sessionId);
        Assert.notNull(beforeSessionId, UserEnum.ValidateCodeEnum.VALIDATE_CODE_ERR.value());
        Assert.isTrue(code.equals(beforeSessionId), UserEnum.ValidateCodeEnum.VALIDATE_CODE_EXPIRE.value());
    }

}
