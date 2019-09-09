package xin.spring.aspect

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import xin.spring.annotation.DocLog
import xin.spring.service.MailLogService
import org.aspectj.lang.reflect.MethodSignature
import java.util.Date
import xin.spring.domain.MailLog
import xin.spring.annotation.SysMailLog
import com.google.gson.Gson
import xin.spring.aspect.utils.HttpContextUtils
import xin.spring.aspect.utils.IPUtils
import org.springframework.stereotype.Service
import org.springframework.core.annotation.Order

@DocLog("AOP切面记录日志")
@Aspect
@Component
class MailLogAspect {
	
	@DocLog("记录MailLog服务")
	@Autowired
	lateinit var mailLogService: MailLogService
	
	@Pointcut("@annotation(xin.spring.annotation.SysMailLog)")
	fun mailLogPointCut(){
		
	}
	
	@Around("mailLogPointCut()")
	@Throws(Throwable::class)
	fun around(point: ProceedingJoinPoint): Any{
		val beginTime = System.currentTimeMillis()
        //执行方法
        val result = point.proceed()
        //执行时长(毫秒)
        val time = System.currentTimeMillis() - beginTime
        //保存日志
        saveSysMailLog(point, time)
        return result
	}
	
	@DocLog("记录日志")
	private fun saveSysMailLog(joinPoint: ProceedingJoinPoint, time: Long) {
        val signature = joinPoint.signature as MethodSignature
        val method = signature.method
        val mailLog = MailLog()
        val syslog = method.getAnnotation(SysMailLog::class.java)
        if (syslog != null) {
            //注解上的描述
            mailLog.operation = syslog.value
        }
        //请求的方法名
        val className = joinPoint.target.javaClass.name
        val methodName = signature.name
        mailLog.method = "$className.$methodName()"
        //请求的参数
        val args = joinPoint.args
        try {
            val params = Gson().toJson(args)
            mailLog.content = params
        } catch (e: Exception) {
        	e.printStackTrace();
        }

        //获取request
        val request = HttpContextUtils.httpServletRequest
        //设置IP地址
        mailLog.ip = IPUtils.getIpAddr(request)
        mailLog.time = time
        mailLog.date = Date()
        //保存系统日志
        mailLogService.save(mailLog)
		print("日志：" + mailLog.toString());
    }
	
}