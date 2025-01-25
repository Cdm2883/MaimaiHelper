plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.ktor)
}

application {
    mainClass.set("vip.cdms.maimaihelper.ApplicationKt")
}

dependencies {
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.netty)
    implementation(libs.ktor.server.config.yaml)
    implementation(libs.ktor.server.auto.head.response)
    implementation(libs.logback.classic)
    implementation(libs.google.zxing.core)
    implementation(libs.google.zxing.javase)
}
