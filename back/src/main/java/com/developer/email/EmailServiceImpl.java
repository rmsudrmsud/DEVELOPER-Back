package com.developer.email;

import java.util.Random;

import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
 
@Service
public class EmailServiceImpl implements EmailService{
 
    @Autowired
    JavaMailSender emailSender;
 
   // public static final String ePw = createKey();
 
    private MimeMessage createMessage(String to)throws Exception{
    	String ePw = createKey();
    	System.out.println("보내는 대상 : "+ to);
        System.out.println("인증 번호 : "+ ePw);
        MimeMessage  message = emailSender.createMimeMessage();
 
        message.addRecipients(RecipientType.TO, to);//보내는 대상
        message.setSubject("[DEVELOPER] 회원가입 이메일 인증");//제목
 
        String msgg="";
        msgg+= "<div style='margin:100px;'>";
        msgg+= "<div style='text-align: center;'><h1> 안녕하세요 :) </h1></div>";
        msgg+= "<div style='text-align: center;'><h1> DEVELOPER를 가입해주셔서 감사합니다. </h1></div>";
        msgg+= "<br>";
        msgg+= "<div style='text-align: center;'><p>인증 페이지로 돌아가 아래의 코드를 입력해주세요<p></div>";
        msgg+= "<br>";
        msgg+= "<div style='text-align: center;'><p>감사합니다.<p></div>";
        msgg+= "<br>";
        msgg+= "<div align='center' style='border:1px solid black; background-color: #FFFACD; font-family:verdana';>";
        msgg+= "<h3 style='color:blue;'>회원가입 인증 코드</h3>";
        msgg+= "<div style='font-size:130%'>";
        msgg+= "CODE : <strong>";
        msgg+= ePw + "</strong><div><br/> ";
        msgg+= "</div>";
       
        String code = ePw;
        message.setDescription(code);
        message.setText(msgg, "utf-8", "html");//내용
        message.setFrom(new InternetAddress("developer.kosta@gmail.com","DEVELOPER"));//보내는 사람
        return message;

    }
 
    public String createKey() {
        StringBuffer key = new StringBuffer();
        Random rnd = new Random();

        for (int i = 0; i < 8; i++) { // 인증코드 8자리
           int index = rnd.nextInt(3); // 0~2 까지 랜덤

           switch (index) {
           case 0:
              key.append((char) ((int) (rnd.nextInt(26)) + 97));
              // a~z (ex. 1+97=98 => (char)98 = 'b')
              break;
           case 1:
              key.append((char) ((int) (rnd.nextInt(26)) + 65));
              // A~Z
              break;
           case 2:
              key.append((rnd.nextInt(10)));
              // 0~9
              break;
           }
        }
        return key.toString();
     }

    @Override
    public String sendSimpleMessage(String to) throws Exception {
       MimeMessage message = createMessage(to);
       try {// 예외처리
          emailSender.send(message);
          String msg = (String) message.getDescription();
          return msg;
       } catch (MailException es) {
          es.printStackTrace();
          throw new IllegalArgumentException();
       }
    }
  
 }
