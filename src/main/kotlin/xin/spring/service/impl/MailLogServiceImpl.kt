package xin.spring.service.impl

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import xin.spring.annotation.DocLog
import xin.spring.dao.MailLogDao
import xin.spring.domain.MailLog
import xin.spring.service.MailLogService

@DocLog("MailLogService服务日志")
@Service("mailLogService")
open class MailLogServiceImpl: MailLogService {
	
	@DocLog("日志")
	private val log by lazy{LoggerFactory.getLogger(this.javaClass) }

	@DocLog("MailLogDao JPA")
	@Autowired
	lateinit var mailLogDao: MailLogDao
	
	@DocLog("记录日志")
	override fun save(mailLog: MailLog): MailLog? {
		var result = mailLogDao.save(mailLog)
		log.info("新增日志数据：{}", result)
		return result
	}

	@DocLog("查找指定id的日志信息")
	override fun findById(id: Long): MailLog? {
		var result = mailLogDao.findById(id) as MailLog?
		log.info("查询到数据:{}", result)
		return result
	}
	
	@DocLog("查找所有")
	override fun findAll(): List<MailLog> {
		var result = mailLogDao.findAll()
		log.info("查询到一下结果：{}", result)
		return result
	}

}