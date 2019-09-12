package xin.spring.domain

import xin.spring.annotation.DocLog
import java.util.Date
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table
import javax.persistence.GenerationType

@DocLog("MailLog日志实体")
@Entity
@Table(name = "sys_mail_log")
class MailLog {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	var id: Long? = null
	
	@Column(name = "date", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP() COMMENT '记录时间'")
	var date: Date? = null
	
	@Column(name = "content", columnDefinition = "TEXT DEFAULT NULL COMMENT '日志内容'")
	var content: String? = null
	
	@Column(name = "froms", columnDefinition = "VARCHAR(255) DEFAULT NULL COMMENT '邮箱'")
	var froms: String? = null
	
	@Column(name = "operation", columnDefinition = "VARCHAR(255) DEFAULT NULL COMMENT '描述邮箱'")
	var operation: String? = null
	
	@Column(name = "method", columnDefinition = "VARCHAR(255) DEFAULT NULL COMMENT '方法'")
	var method: String? = null
	
	@Column(name = "ip", columnDefinition = "VARCHAR(255) DEFAULT NULL COMMENT 'IP'")
	var ip: String? = null
	
	@Column(name = "time", columnDefinition = "BIGINT(20) DEFAULT '0' COMMENT '执行耗时'")
	var time: Long? = null
	
}