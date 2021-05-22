

object Versions {
    const val compose = "1.0.0-beta07"
    const val coreKtx = "1.5.0"
    const val appcompat = "1.3.0"
    const val material = "1.3.0"
    const val lifecycleRuntimeKtx = "2.3.1"
    const val composeActivity = "1.3.0-alpha08"

    const val jUnit = "4.13.2"
    const val jUnitExt = "1.1.2"
    const val espressoCore = "3.3.0"

    const val kotlin = "1.4.32"
    const val androidToolsGradle = "7.0.0-beta02"
}

object BuildPlugins {
    // All the build plugins are added here
    const val dynamicFeature = "com.android.dynamic-feature"
    const val androidLibrary = "com.android.library"
    const val kapt = "kotlin-kapt"
    const val ktlintPlugin = "org.jlleitschuh.gradle.ktlint"
    const val detektPlugin = "io.gitlab.arturbosch.detekt"
    const val dokkaPlugin = "org.jetbrains.dokka"
    const val spotlessPlugin = "com.diffplug.spotless"
    const val androidApplication = "com.android.application"
    const val kotlinAndroid = "kotlin-android"
    const val kotlinParcelizePlugin = "org.jetbrains.kotlin.plugin.parcelize"
    const val gradleVersionsPlugin = "com.github.ben-manes.versions"
    const val slackKeeper = "com.slack.keeper"
    const val jacocoAndroid = "com.hiya.jacoco-android"
    const val safeargs = "androidx.navigation.safeargs.kotlin"
    const val googleServices = "com.google.gms.google-services"
    const val firebaseCrashlytics = "com.google.firebase.crashlytics"
}

object GradlePlugin {
    const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val android = "com.android.tools.build:gradle:${Versions.androidToolsGradle}"
}

object Libraries {
    // compose UI
    const val composeUi = "androidx.compose.ui:ui:${Versions.compose}"
    const val composeMaterial = "androidx.compose.material:material:${Versions.compose}"
    const val composeTooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
    const val composeActivity = "androidx.activity:activity-compose:${Versions.composeActivity}"

    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"

    const val appCompat = "androidx.appcompat:appcompat:${Versions.appcompat}"

    const val material = "com.google.android.material:material:${Versions.material}"

    const val lifecycleRuntimeKtx =
        "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleRuntimeKtx}"
}

object TestLibraries {
    const val jUnit = "junit:junit:${Versions.jUnit}"
    const val jUnitExt = "androidx.test.ext:junit:${Versions.jUnitExt}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
    const val composeUiTestJUnit = "androidx.compose.ui:ui-test-junit4:${Versions.compose}"
}

object BuildModules {
    const val appModule = ":app"
    const val coreModule = ":internal:core"
    const val dataModule = ":internal:data"
    const val domainModule = ":internal:domain"
}

object AndroidSdk {
    const val minSdkVersion = 21
    const val compileSdkVersion = 30
    const val buildToolsVersion = "30.0.3"
    const val targetSdkVersion = compileSdkVersion
    const val versionCode = 6
    const val versionName = "0.0.4"
}
