package com.crumb.fiftheditionsheet

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform