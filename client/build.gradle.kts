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
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}
