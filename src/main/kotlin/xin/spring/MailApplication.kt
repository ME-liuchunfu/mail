package xin.spring

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.EnableAspectJAutoProxy

@SpringBootApplication
@EnableAspectJAutoProxy
open class MailApplication

fun main(args: Array<String>) {
	runApplication<MailApplication>(*args)
}
