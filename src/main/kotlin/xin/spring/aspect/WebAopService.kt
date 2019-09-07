package xin.spring.aspect

import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.annotation.Pointcut
import org.springframework.stereotype.Service
import xin.spring.annotation.AnnotationTest

@Aspect
@Service
class WebAopService {
	
	@Pointcut("@annotation(xin.spring.annotation.AnnotationTest)")
    fun testPoint(){
    }

    @Before("testPoint()")
    fun test1(joinPoint: JoinPoint){
        println(">>>${joinPoint.signature},${joinPoint.target.javaClass.name}")  //返回方法签名和目标对象的类名
    }

    @Before("testPoint() && @annotation(test)") //@annotation(test)获取注解对象
    fun test2(joinPoint: JoinPoint, test: AnnotationTest){
        println(">>>${test.value}") //注解的value值
    }
	
}
