plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.dagger.hilt)
    alias(libs.plugins.ksp)
    alias(libs.plugins.serialization.plugin)}

android {
    namespace = "com.example.paymobtechnicaltask"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.paymobtechnicaltask"
        minSdk = 21
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    //Navigation
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.navigation.ui)

    //Coroutines
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    //Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    //Kotlin Serialization
    implementation(libs.kotlinx.serialization.json)

    //Retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit2.kotlinx.serialization.converter)
    implementation(libs.jakewharton.retrofit2.kotlin.coroutines.adapter)
    implementation(libs.okhttp)
    implementation(libs.gson)

    //Glide
    implementation(libs.glide)

    //Paging
    implementation(libs.androidx.paging.runtime)

    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}