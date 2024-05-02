plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")  // Changed from annotationProcessor to kapt
    id("androidx.navigation.safeargs.kotlin")
}


android {
    namespace = "com.example.login_screen_test"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.login_screen_test"
        minSdk = 24
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
    buildFeatures {
        dataBinding = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.13.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")
    implementation("com.google.ar:core:1.42.0")
    implementation("com.google.firebase:firebase-crashlytics-buildtools:2.9.9")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // navigation not change any verstion Kotlin
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.7")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.7")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-simplexml:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")
    // Coroutines (for handling asynchronous tasks)
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")

    //http logcat
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-savedstate:2.7.0")
    implementation("androidx.fragment:fragment-ktx:1.6.2")

    // Kotlinx Serialization dependency
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")

    //Editext
    implementation("com.google.android.material:material:1.11.0")

    // Glide
    implementation ("com.github.bumptech.glide:glide:4.16.0")
    //noinspection KaptUsageInsteadOfKsp
    kapt ("com.github.bumptech.glide:compiler:4.16.0")
    implementation ("de.hdodenhof:circleimageview:3.1.0")

    implementation("it.xabaras.android:recyclerview-swipedecorator:1.4")

    // logcat dependency for logput
    implementation("com.elvishew:xlog:1.11.0")

//    implementation("com.github.jainsahab:Snooper:1.5.5@aar")
//    implementation("com.github.jainsahab:Snooper-Spring:1.5.5@aar")
//    implementation("com.github.jainsahab:Snooper-Okhttp:1.5.5@aar")





}