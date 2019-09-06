package xin.spring.annotation

@DocLog("注释注解")
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.TYPE, AnnotationTarget.FIELD, AnnotationTarget.FUNCTION, AnnotationTarget.LOCAL_VARIABLE, AnnotationTarget.CLASS, AnnotationTarget.PROPERTY)
@MustBeDocumented
annotation class DocLog(val value: String = "") {
	
}