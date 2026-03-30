package io.github.neronguyenvn.kmp_nerochatapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform