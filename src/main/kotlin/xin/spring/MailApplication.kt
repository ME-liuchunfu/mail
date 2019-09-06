package xin.spring

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class MailApplication

fun main(args: Array<String>) {
	runApplication<MailApplication>(*args)
}
