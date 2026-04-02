package io.github.neronguyenvn.nerochat

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform