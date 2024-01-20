package extensions

fun String.fullModulePath(domain: String): String = ":${domain.trim{ it == ':'}}:${this.trim{it == ':'}}"