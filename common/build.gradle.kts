import dependencies.Dependencies
import dependencies.AnnotationProcessorsDependencies

plugins {
    id("commons.android-library")
}

dependencies {
    api(Dependencies.APPCOMPAT)
    api(Dependencies.CORE_KTX)
    implementation(Dependencies.RECYCLE_VIEW)
    implementation(Dependencies.SWIPE_REFRESH_LAYOUT)
    implementation(Dependencies.RX_JAVA)
    implementation(Dependencies.RX_ANDROID)
    implementation(Dependencies.TIMBER)
    implementation(Dependencies.DAGGER)
    implementation(Dependencies.GLIDE)

    kapt(AnnotationProcessorsDependencies.GLIDE)
    kapt(AnnotationProcessorsDependencies.DAGGER)
}