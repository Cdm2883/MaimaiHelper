plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.ktor) apply false
}

version = libs.versions.maimaihelper.name.get()
subprojects { version = rootProject.version }
