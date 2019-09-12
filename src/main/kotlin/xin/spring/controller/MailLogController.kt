package xin.spring.controller

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import xin.spring.annotation.DocLog
import xin.spring.result.R
import xin.spring.service.MailLogService
import org.springframework.beans.factory.annotation.Autowired

@DocLog("查询邮件发送记录")
@RestController
@RequestMapping("/sys/mail/")
class MailLogController {
	
	@DocLog("邮件数据服务")
	@Autowired
	lateinit var mailLogService: MailLogService
	
	@RequestMapping("list")
	fun findMailList(): R{
		var result = mailLogService.findAll()
		return R.ok().put("data", result)
	}
	
}