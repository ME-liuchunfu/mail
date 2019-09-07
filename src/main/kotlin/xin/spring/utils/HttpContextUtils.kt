package xin.spring.aspect.utils

import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import javax.servlet.http.HttpServletRequest
import xin.spring.annotation.DocLog

@DocLog("HTTP工具类")
object HttpContextUtils {

    val httpServletRequest: HttpServletRequest
        get() = (RequestContextHolder.getRequestAttributes() as ServletRequestAttributes).request

    val domain: String
        get() {
            val request = httpServletRequest
            val url = request.requestURL
            return url.delete(url.length - request.requestURI.length, url.length).toString()
        }

    val origin: String
        get() {
            val request = httpServletRequest
            return request.getHeader("Origin")
        }
}
