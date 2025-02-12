import java.util.Properties

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.coinscope.data"

    buildFeatures.buildConfig = true

    defaultConfig {
        compileSdk = libs.versions.compileSdk.get().toInt()
        minSdk = libs.versions.minSdk.get().toInt()
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    val localProperties = Properties().apply {
        load(rootProject.file("local.properties").inputStream())
    }
    val apiKey: String = localProperties.getProperty("API_KEY") ?: ""

    buildTypes {
        val baseUrl = "\"https://api.coingecko.com/api/v3/\""

        all {
            buildConfigField("String", "BASE_URL", baseUrl)
            buildConfigField("String", "API_KEY", "\"$apiKey\"")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation(project(":domain"))

    implementation(libs.retrofit)
    implementation(libs.okhttp)
    implementation(libs.gson)
    implementation(libs.gson.converter)

    implementation(libs.koin.core)
    implementation(libs.koin.android)

    testImplementation(libs.arch.core.testing)
    testImplementation(libs.junit)
    testImplementation(libs.mockk)
    testImplementation(libs.coroutines.test)
}