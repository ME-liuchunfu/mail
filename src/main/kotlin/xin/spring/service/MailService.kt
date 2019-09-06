package xin.spring.service

import xin.spring.annotation.DocLog
import org.slf4j.LoggerFactory

@DocLog("消息发送服务")
interface MailService {
	
	@DocLog("发送普通文本邮件， to: 收件人， subject： 主题， content：内容")
	fun sendSimpleMail(to: String, subject: String, content: String)
	
	@DocLog("发送HTML邮件， to：收件人，subject：主题，content：内容")
	fun sendHtmlMail(to: String, subject: String, content: String)
	
	@DocLog("发送带附件的邮件，to：收件人，subject：主题，content：内容，filePath： 附件内容")
	fun sendAttachmentMail(to: String, subject: String, content: String, filePath: String)
	
	@DocLog("发送带图片的邮件，to：收件人，subject：主题，content：内容，rscPath：图片路径，rscId：图片ID，用于在<img>标签中使用，从而显示图片")
	fun sendInlineResourceMail(to: String, subject: String, content: String, rscPath: String, rscId: String)
	
}