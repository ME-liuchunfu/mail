package xin.spring.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import xin.spring.annotation.DocLog
import xin.spring.annotation.SysMailLog
import xin.spring.result.R
import xin.spring.service.MailService

@DocLog("邮件发送控制器")
@RestController
@RequestMapping("/sys/")
open class MailController {
	
	@DocLog("消息服务")
	@Autowired
	open lateinit var mailService: MailService
	
	@SysMailLog("单发邮件")
	@DocLog("单发邮件")
	@RequestMapping("sendSimpleMail")
	fun sendSimpleMail(@RequestParam(name = "to", required = true) to: String,
					   @RequestParam(name = "subject", required = false, defaultValue = "") subject: String,
					   @RequestParam(name = "content", required = false, defaultValue = "") content: String): R{
		mailService.sendSimpleMail(to, subject, content)
		return R.ok()
	}
	
	@SysMailLog("单发邮件")
	@DocLog("多次单发邮件")
	@RequestMapping("sendSimpleMailMore")
	fun sendSimpleMailMore(@RequestParam(name = "to", required = true) to: String,
					   @RequestParam(name = "subject", required = false, defaultValue = "") subject: String,
					   @RequestParam(name = "content", required = false, defaultValue = "") content: String,
					   @RequestParam(name = "size", required = false, defaultValue = "1") size: Int): R{
		for(i in 0 until size){			
			mailService.sendSimpleMail(to, subject, content)
		}
		return R.ok()
	}
	
}