package xin.spring.service.impl

import org.springframework.beans.factory.annotation.Value
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import xin.spring.annotation.DocLog
import xin.spring.service.MailService
import xin.spring.annotation.DocExample
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.mail.SimpleMailMessage
import javax.mail.internet.MimeMessage
import org.springframework.mail.javamail.MimeMessageHelper
import javax.mail.MessagingException
import org.springframework.core.io.FileSystemResource
import java.io.File

@DocLog("消息发送服务实现类")
@Service("mailService")
class MailServiceImpl: MailService {
	
	@DocLog("日志")
	private val logger by lazy {LoggerFactory.getLogger(this.javaClass)}
	
	@Value("\${spring.mail.username}")
	lateinit var from: String
	
	@Autowired
	lateinit var mailSender: JavaMailSender
	
	@DocLog("发送普通文本邮件， to: 收件人， subject： 主题， content：内容")
	@DocExample("service.sendSimpleMail(\"receiver@email.com\", \"发送邮件测试\", \"大家好，这是我用springboot进行发送邮件测试\");")
	override fun sendSimpleMail(to: String, subject: String, content: String) {
		logger.info("发送普通文本邮件开始：{},{},{}", to, subject, content);
		var message: SimpleMailMessage = SimpleMailMessage()
		message.setTo(to)
		message.setSubject(subject)
		message.setText(content)
		message.setFrom(from)
		mailSender.send(message)
		logger.info("发送普通文本邮件完成");
	}

	@DocLog("发送HTML邮件， to：收件人，subject：主题，content：内容")
	@DocExample("var content: String = \"<html><body><h3><font color=\"red\">大家好，这是springboot发送的HTML邮件</font></h3></body></html>\""
				+"service.sendHtmlMail(\"receiver@email.com\", \"发送邮件测试\", content);")
	override fun sendHtmlMail(to: String, subject: String, content: String) {
		logger.info("发送HTML邮件开始：{},{},{}", to, subject, content);
        var message: MimeMessage = mailSender.createMimeMessage();
        var helper: MimeMessageHelper;
        try {
            helper = MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);//true代表支持html
            mailSender.send(message);
            logger.info("发送HTML邮件成功");
        } catch (e: MessagingException) {
            logger.error("发送HTML邮件失败：", e);
        }    
	}

	@DocLog("发送带附件的邮件，to：收件人，subject：主题，content：内容，filePath： 附件内容")
	@DocExample(" var content: String = \"<html><body><h3><font color=\"red\">大家好，这是springboot发送的HTML邮件，有附件哦</font></h3></body></html>\";"
				+ "service.sendAttachmentMail(\"receiver@email.com\", \"发送邮件测试\", content, filePath);")
	override fun sendAttachmentMail(to: String, subject: String, content: String, filePath: String) {
		logger.info("发送带附件邮件开始：{},{},{},{}", to, subject, content, filePath);
        var message: MimeMessage = mailSender.createMimeMessage();
        
        var helper: MimeMessageHelper;
        try {
            helper = MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);
            var file: FileSystemResource = FileSystemResource(File(filePath));
            var fileName = file.getFilename();
            helper.addAttachment(fileName, file);//添加附件，可多次调用该方法添加多个附件  
            mailSender.send(message);
            logger.info("发送带附件邮件成功");
        } catch (e: MessagingException) {
            logger.error("发送带附件邮件失败", e);
        }
	}

	@DocLog("发送带图片的邮件，to：收件人，subject：主题，content：内容，rscPath：图片路径，rscId：图片ID，用于在<img>标签中使用，从而显示图片")
	@DocExample("var rscPath: String = \"your picture path\";"
				+ "var rscId: String = \"001\";"
				+ "var content: String = \"<html><body><h3><font color=\"red\">大家好，这是springboot发送的HTML邮件，有图片哦</font></h3>\""
                         + "<img src='cid: rscId '></body></html>\";")
	override fun sendInlineResourceMail(to: String, subject: String, content: String, rscPath: String, rscId: String) {
		logger.info("发送带图片邮件开始：{},{},{},{},{}", to, subject, content, rscPath, rscId);
        var message: MimeMessage = mailSender.createMimeMessage();
        
        var helper: MimeMessageHelper;
        try {
            helper = MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);
            var res: FileSystemResource = FileSystemResource(File(rscPath));
            helper.addInline(rscId, res);//重复使用添加多个图片
            mailSender.send(message);
            logger.info("发送带图片邮件成功");
        } catch (e: MessagingException) {
            logger.error("发送带图片邮件失败", e);
        }
	}
}