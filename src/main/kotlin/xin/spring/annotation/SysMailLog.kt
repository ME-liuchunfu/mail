package xin.spring.annotation

@DocLog("AOP切面邮件日志记录注解")
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
annotation class SysMailLog(val value: String = "") {
}