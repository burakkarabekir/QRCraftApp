import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.androidx.room)
}

android {
    namespace = "com.bksd.qrcraftapp"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.bksd.qrcraftapp"
        minSdk = 26
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        vectorDrawables { useSupportLibrary = true }
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        ksp { arg("room.schemaLocation", "$projectDir/schemas") }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlin {
        compilerOptions {
            jvmTarget = JvmTarget.JVM_17
        }
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {
    implementation(platform(libs.compose.bom))

    // Core AndroidX
    implementation(libs.core.ktx)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.activity.compose)

    // Compose UI
    implementation(libs.bundles.compose.core)
    implementation(libs.androidx.foundation.layout)
    implementation(libs.androidx.material3.window.size.class1)
    debugImplementation(libs.bundles.compose.debug)

    // Material3
    implementation(libs.compose.material3)

    // Navigation 3 (new API)
    implementation(libs.bundles.nav3)

    // DI: Koin
    implementation(libs.bundles.koin)

    // CameraX + ML Kit
    implementation(libs.bundles.camerax)
    implementation(libs.bundles.qr)

    // Data / Persistence
    implementation(libs.datastore.preferences)
    implementation(libs.bundles.room)
    ksp(libs.room.compiler)

    // Serialization & utils
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.coil)

    // Logging
    implementation(libs.timber)

    // Splash
    implementation(libs.splashscreen)

    // Tests
    testImplementation(libs.bundles.testing.unit)
    testImplementation(libs.androidx.ui.test.junit4)
    androidTestImplementation(libs.bundles.testing.android)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.ui.test.junit4)
    androidTestImplementation(libs.mockito.android)
    androidTestImplementation(libs.mockito.core)
}

room {
    schemaDirectory("$projectDir/schemas")
}
