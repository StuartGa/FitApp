
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
    id("com.google.gms.google-services") version "4.4.2"

}

ksp {
    allWarningsAsErrors = true
}

android {
    namespace = "com.example.fitapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.fitapp"
        minSdk = 29
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
        freeCompilerArgs += "-Xcontext-receivers"

    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(platform(libs.firebase))
    implementation(libs.firebase.firestore.ktx)
    implementation(libs.google.firebase.auth)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.connect.client)


    //DataStore
    implementation (libs.androidx.datastore.preferences)
    // Room dependencies
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.material3.android)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.storage)
    implementation(libs.androidx.storage)
    implementation(libs.accessibility.test.framework)
    implementation(libs.accessibility.test.framework)
    ksp(libs.androidx.room.compiler)



    //Permission manager
    implementation(libs.accompanist.permissions)

    // Work Manager
    implementation(libs.androidx.work.runtime.ktx)


    // Hilt dependencies
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.hilt.work)
    implementation(libs.androidx.hilt.common)



    // Testing dependencies
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}

