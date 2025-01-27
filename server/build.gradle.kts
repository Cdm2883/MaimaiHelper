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
    implementation(libs.logback.classic)
    implementation(libs.google.zxing.core)
    implementation(libs.google.zxing.javase)
}

tasks.register("buildFatJarCustom") {
    dependsOn("buildFatJar")
    doLast {
        buildDir("libs/server-all.jar")
            .renameTo(buildDir("libs/maimaihelper-server-$version.jar"))
    }
}

fun buildDir(path: String) = layout.buildDirectory.file(path).get().asFile
