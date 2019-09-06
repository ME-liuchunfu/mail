package xin.spring.result

import xin.spring.annotation.DocLog
import java.util.HashMap
import org.apache.http.HttpStatus

@DocLog("返回数据")
class R : HashMap<String, Any>() {
    init {
        put("code", 0)
        put("msg", "success")
    }

    override fun put(key: String, value: Any): R {
        super.put(key, value)
        return this
    }

    companion object {
        fun error(msg: String): R {
            return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, msg)
        }

        @JvmOverloads
        fun error(code: Int = HttpStatus.SC_INTERNAL_SERVER_ERROR, msg: String = "未知异常，请联系管理员"): R {
            val r = R()
            r["code"] = code
            r["msg"] = msg
            return r
        }

        fun ok(msg: String): R {
            val r = R()
            r["msg"] = msg
            return r
        }

        fun ok(map: Map<String, Any>): R {
            val r = R()
            r.putAll(map)
            return r
        }

        fun ok(): R {
            return R()
        }
    }
	
}
