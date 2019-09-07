package xin.spring.dao

import org.springframework.data.jpa.repository.JpaRepository
import xin.spring.annotation.DocLog
import xin.spring.domain.MailLog
import org.springframework.stereotype.Repository

@DocLog("MailLog日志")
@Repository("mailLogDao")
interface MailLogDao: JpaRepository<MailLog, Long>{
	
	
	
}