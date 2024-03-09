plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id ("kotlin-kapt")
    id ("com.google.dagger.hilt.android")
    id ("androidx.navigation.safeargs")
    id ("kotlin-parcelize")
    id ("org.jetbrains.kotlin.plugin.serialization")
}

android {
    namespace = "com.huntams.network"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
    buildFeatures {
        viewBinding = true
    }
    kapt {
        correctErrorTypes = true
    }
}

dependencies {
    implementation ("androidx.paging:paging-runtime-ktx:${Versions.paging}")
    implementation(project(":core:model"))
    debugImplementation ("com.github.chuckerteam.chucker:library:${Versions.chucker}")
    releaseImplementation ("com.github.chuckerteam.chucker:library-no-op:${Versions.chucker}")
    implementation ("io.coil-kt:coil:${Versions.coil}")
    implementation ("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:${Versions.retrofitSerialization}")
    implementation ("org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.kotlinSerialization}")
    implementation ("com.squareup.retrofit2:retrofit:${Versions.retrofit}")
    implementation ("com.google.dagger:hilt-android:${Versions.hilt}")
    kapt ("com.google.dagger:hilt-compiler:${Versions.hilt}")
    implementation("androidx.core:core-ktx:${Versions.coreKtx}")
    testImplementation("junit:junit:${Versions.junit}")
    androidTestImplementation("androidx.test.ext:junit:${Versions.extJunit}")
    androidTestImplementation("androidx.test.espresso:espresso-core:${Versions.espresso}")
}