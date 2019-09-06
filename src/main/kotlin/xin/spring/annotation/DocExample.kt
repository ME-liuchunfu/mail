package xin.spring.annotation

@DocLog("方法使用注解")
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
annotation class DocExample(val value: String) {
	
}