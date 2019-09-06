package xin.spring.exception

import xin.spring.annotation.DocLog

@DocLog(" 自定义异常")
class RRException : RuntimeException {

    var msg: String? = null
    var code = 500

    constructor(msg: String) : super(msg) {
        this.msg = msg
    }

    constructor(msg: String, e: Throwable) : super(msg, e) {
        this.msg = msg
    }

    constructor(msg: String, code: Int) : super(msg) {
        this.msg = msg
        this.code = code
    }

    constructor(msg: String, code: Int, e: Throwable) : super(msg, e) {
        this.msg = msg
        this.code = code
    }
	
}
