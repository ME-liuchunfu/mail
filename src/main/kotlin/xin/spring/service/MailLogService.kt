package xin.spring.service

import xin.spring.annotation.DocLog
import xin.spring.domain.MailLog

@DocLog("MailLogService邮件日志")
interface MailLogService {
	
	@DocLog("记录日志")
	fun save(mailLog: MailLog): MailLog?
	
	@DocLog("查找指定id的日志信息")
	fun findById(id: Long): MailLog?
	
	@DocLog("查找所有")
	fun findAll(): List<MailLog>
	
}