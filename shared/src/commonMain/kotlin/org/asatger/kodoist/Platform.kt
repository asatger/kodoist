package org.asatger.kodoist

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform