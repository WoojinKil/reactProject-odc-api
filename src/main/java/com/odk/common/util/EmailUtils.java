package com.odk.common.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
@RequiredArgsConstructor
public class EmailUtils {

	@Autowired
	private JavaMailSender mailSender;
	@Value("${spring.mail.username}")
 	String emailFromSender;
	
	/**
	 * 이메일코드 안내 메일 작성
	 * @param domain
	 * @param url
	 * @return
	 */
	public static String getEmailVerificationContents (String certType, String code) {
		String contents = "";
		StringBuffer buffer = new StringBuffer();
		// 이메일 내용 작성
		buffer.append("<html>");
		buffer.append("<head>");
		buffer.append("<meta charset=\"utf-8\">");
		buffer.append("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
		buffer.append("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\" />");
		buffer.append("</head>");
		buffer.append("<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">");
		buffer.append("<tr>");
		buffer.append("<td align=\"center\">");
		buffer.append("<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width: 750px; border:1px solid #ddd;\">");
		buffer.append("<tr>");
		buffer.append("<td align=\"center\">");
		buffer.append("<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width: 650px;\" >");
		buffer.append("<tr>");
		buffer.append("<td align=\"center\" valign=\"top\" style=\"padding: 25px 0; background:#FFF\" >");
		buffer.append("</td>");
		buffer.append("</tr>");
		buffer.append("<tr>");
		buffer.append("<td style=\"font-size:24px;line-height:28px;color:#666;text-align:center;padding: 25px 0; \"> 인증메일 발송</td>");
		buffer.append("</tr>");
		buffer.append("</table>");
		buffer.append("</td>");
		buffer.append("</tr>");
		buffer.append("<tr>");
		buffer.append("<td align=\"center\" style=\"background:#FFF\">");
		buffer.append("<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width: 650px;\" >");
		buffer.append("<tr>");
		buffer.append("<td>");
		buffer.append("<!-- HERO IMAGE -->");
		buffer.append("<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"background: #f6f6f6;\">");
		buffer.append("<tr>");
		buffer.append("<td>");
		buffer.append("<!-- COPY -->");
		buffer.append("<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">");
		buffer.append("<tr>");
		buffer.append("<td align=\"center\" style=\"font-size: 25px; font-family: Helvetica, Arial, sans-serif; color:#666; padding:25px\">"+certType+"</td>");
		buffer.append("</tr>");
		buffer.append("<tr>");
		buffer.append("<td align=\"center\" style=\"padding: 20px 0 0 0; font-size: 16px; line-height: 25px; font-family: Helvetica, Arial, sans-serif; color: #666666;\">");
		buffer.append(" 인증코드입니다.<br/>인증코드 : <b>" + code + "</b>");
		buffer.append("</td>");
		buffer.append("</tr>");
		buffer.append("</table>");
		buffer.append("</td>");
		buffer.append("</tr>");
		buffer.append("<tr>");
		buffer.append("<td align=\"center\">");
		buffer.append("<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">");
		buffer.append("<tr>");
		buffer.append("<td align=\"center\" style=\"padding: 25px;\">");
		buffer.append("<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\">");
		buffer.append("<tr>");
		buffer.append("<td align=\"center\" style=\"border-radius: 3px;\" bgcolor=\"#256F9C\">");
		buffer.append("</td>");
		buffer.append("</tr>");
		buffer.append("</table>");
		buffer.append("</td>");
		buffer.append("</tr>");
		buffer.append("</table>");
		buffer.append("</td>");
		buffer.append("</tr>");
		buffer.append("</table>");
		buffer.append("</td>");
		buffer.append("</tr>");
		buffer.append("</table>");
		buffer.append("</td>");
		buffer.append("</tr>");
		buffer.append("<tr>");
		buffer.append("<td bgcolor=\"#ffffff\" align=\"center\" style=\"padding: 25px 0px;\">");
		buffer.append("<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" style=\"max-width: 650px;\" >");
		buffer.append("<tr>");
		buffer.append("<td style=\"font-size:13px;line-height:20px;color:#666;text-align:center\">본 메일은 메일 수신 동의에 의한 발신 전용 메일입니다.<br />자세한 문의 사항은 관리자에게 문의 주시기 바랍니다.</td>");
		buffer.append("</tr>");
		buffer.append("</table>");
		buffer.append("</td>");
		buffer.append("</tr>");
		buffer.append("</table>");
		buffer.append("</td>");
		buffer.append("</tr>");
		buffer.append("</table>");
		buffer.append("</html>");
		// 리턴값 설정
		contents = buffer.toString();
		return contents;
	}
	
	
	@Transactional
    public void sendMimeMessage(String to, String subject, String text) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        	
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            helper.setTo(to);
            helper.setFrom(emailFromSender);
            helper.setSubject(subject);
            helper.setText(text, true);  // true indicates HTML content
            mailSender.send(mimeMessage);

        } catch (MessagingException e) {
            e.printStackTrace();
            // Handle exception or rethrow it
        }
    }
}
