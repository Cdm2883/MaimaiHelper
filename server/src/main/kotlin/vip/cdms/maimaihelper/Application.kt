package vip.cdms.maimaihelper

import io.ktor.server.application.*
import io.ktor.server.plugins.autohead.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    QrCapture.init()
    install(AutoHeadResponse)
    helperRouting()
}
