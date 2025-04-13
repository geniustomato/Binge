plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.google.dagger.hilt)
}

android {
    namespace = "com.clooy.binge"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.clooy.binge"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

kapt {
    correctErrorTypes = true
}

// TODO Temporary workaround? https://github.com/mockk/mockk/issues/1171
tasks.withType<Test> {
    useJUnitPlatform()
    jvmArgs = listOf("-XX:+EnableDynamicAgentLoading")
}

dependencies {

    implementation(libs.kotlinx.serialization.json)

    // AndroidX
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    // Compose
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.compose.navigation.compose)

    // Retrofit
    implementation(libs.squareup.retrofit)
    implementation(libs.squareup.retrofit.gson)

    // Coroutines
    implementation(libs.jetbrains.kotlinx.coroutines.core)
    implementation(libs.jetbrains.kotlinx.coroutines.android)

    // Dagger Hilt
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.google.dagger.hilt.android)
    kapt(libs.google.dagger.hilt.compiler)

    // Utils
    implementation(libs.threetenabp)

    // Testing
    testImplementation(libs.junit4)
    testImplementation(libs.junit5.jupiter.api)
    testRuntimeOnly(libs.junit5.jupiter.engine)
    testImplementation(libs.mockk)
    testImplementation(libs.kotlinx.coroutines.test)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}