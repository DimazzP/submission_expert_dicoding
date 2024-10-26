plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("kotlin-parcelize")
    id("org.jetbrains.kotlin.kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.example.submission_navigation"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.submission_navigation"
        minSdk = 27
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {

    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        buildConfig = true
    }
    viewBinding {
        enable = true
    }
    dynamicFeatures += setOf(":favorite")
}

dependencies {

    implementation(project(":core"))
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.code.gson:gson:2.10.1")
    implementation ("com.github.bumptech.glide:glide:4.12.0")
    implementation ("com.squareup.okhttp3:okhttp:4.9.1")
    implementation ("com.squareup.okhttp3:logging-interceptor:4.9.1")
    implementation("com.squareup.retrofit2:retrofit:2.10.0")
    implementation("com.squareup.retrofit2:converter-gson:2.10.0")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.viewpager2:viewpager2:1.0.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.7")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.7")
    implementation("androidx.room:room-runtime:2.6.1")
    implementation("androidx.datastore:datastore-preferences:1.0.0")
    implementation("com.google.android.ads:mediation-test-suite:3.0.0")
    implementation("androidx.test.espresso:espresso-contrib:3.5.1")
    implementation("com.google.dagger:hilt-android:2.51.1")
//    implementation("com.google.android.play:core:1.10.3")
//    implementation("com.google.android.play:core-ktx:1.10.3")
    implementation("com.google.android.play:core:1.10.3")
    kapt("com.google.dagger:hilt-android-compiler:2.51.1")
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
    ksp("androidx.room:room-compiler:2.6.1")
    testImplementation("org.mockito:mockito-core:4.4.0")
    testImplementation("org.mockito:mockito-inline:4.4.0")
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.5.2")
    testImplementation("androidx.arch.core:core-testing:2.2.0")
    debugImplementation("androidx.arch.core:core-testing:2.2.0")
    debugImplementation("androidx.test.espresso:espresso-core:3.4.0")
    debugImplementation("androidx.fragment:fragment-testing:1.4.0")
    debugImplementation("androidx.test.ext:junit-ktx:1.1.5")
    debugImplementation("androidx.test:rules:1.5.0")
    debugImplementation("androidx.test:runner:1.5.2")
    debugImplementation("androidx.test.ext:junit:1.1.5")
    debugImplementation("androidx.test.espresso:espresso-core:3.4.0")
    debugImplementation("org.mockito:mockito-core:4.4.0")
    debugImplementation("org.mockito:mockito-inline:4.4.0")
}