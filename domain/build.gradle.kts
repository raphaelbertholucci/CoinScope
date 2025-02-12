plugins {
    id("kotlin")
    id("kotlin-kapt")
}

dependencies {
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.koin.core)
}