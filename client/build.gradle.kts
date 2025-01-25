plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "vip.cdms.maimaihelper"
    compileSdk = 34

    defaultConfig {
        applicationId = "vip.cdms.maimaihelper"
        minSdk = 16
        //noinspection ExpiredTargetSdkVersion
        targetSdk = 27
        versionCode = libs.versions.maimaihelper.code.get().toInt()
        versionName = libs.versions.maimaihelper.name.get()
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        create("preview") {
            initWith(getByName("release"))
            versionNameSuffix = "-preview"
            applicationIdSuffix = ".preview"
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}
