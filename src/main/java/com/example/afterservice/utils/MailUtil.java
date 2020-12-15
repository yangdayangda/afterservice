package com.example.afterservice.utils;

import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MailUtil {

    @Value("${email.FROM}")
    public  String FROM;

    @Value("${email.AUTH}")
    public  String AUTH;

    @Value("${email.hostname}")
    public  String hostname;


    /**
     * 向邮箱发送验证码
     * @param emailaddress
     * @param code
     * @return
     */
    //邮箱验证码
    public  boolean sendEmail(String emailaddress,String code){
        try {
            HtmlEmail email = new HtmlEmail();//不用更改
            email.setHostName(hostname);//需要修改，126邮箱为smtp.126.com,163邮箱为163.smtp.com，QQ为smtp.qq.com
            email.setCharset("UTF-8");
            email.addTo(emailaddress);// 收件地址

            email.setFrom(FROM, "管理员");//此处填邮箱地址和用户名,用户名可以任意填写

            email.setAuthentication(FROM, AUTH);//此处填写邮箱地址和客户端授权码

            email.setSubject("软件售后服务通讯");//此处填写邮件名，邮件名可任意填写
            email.setMsg("【软件售后服务】您的验证码是:" + code+",在10分钟内有效。如非本人操作请忽略本短信。");//此处填写邮件内容

            email.send();
            return true;
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

}
