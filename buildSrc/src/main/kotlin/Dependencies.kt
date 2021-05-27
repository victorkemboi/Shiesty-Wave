

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

    const val composeNavigation = "2.4.0-alpha01"

    // DI - KOIN
    const val koin = "3.0.2"
    const val koinCompose = "3.0.1"
    const val accompanist = "0.10.0"

    const val truth = "1.0.1"

    const val room = "2.4.0-alpha02"

    // Joda Time
    const val joda = "2.10.10"

    const val pagingCompose = "1.0.0-alpha09"

    const val timber = "4.7.1"
}

object BuildPlugins {
    // All the build plugins are added here
    const val dynamicFeature = "com.android.dynamic-feature"
    const val androidLibrary = "com.android.library"
    const val kapt = "kotlin-kapt"
    const val ksp = "com.google.devtools.ksp"
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

    const val composeNavigation =
        "androidx.navigation:navigation-compose:${Versions.composeNavigation}"

    // DI - KOIN
    const val koin = "io.insert-koin:koin-android:${Versions.koin}"
    const val koinCompose = "io.insert-koin:koin-androidx-compose:${Versions.koinCompose}"

    const val accompanist = "com.google.accompanist:accompanist-insets:${Versions.accompanist}"
    const val accompanistSystemUiController =
        "com.google.accompanist:accompanist-systemuicontroller:${Versions.accompanist}"

    // Room DB and Store
    const val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
    const val room = "androidx.room:room-ktx:${Versions.room}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"

    // Joda Time
    const val joda = "joda-time:joda-time:${Versions.joda}"

    // paging compose
    const val pagingCompose = "androidx.paging:paging-compose:${Versions.pagingCompose}"

    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
}

object TestLibraries {
    const val jUnit = "junit:junit:${Versions.jUnit}"
    const val jUnitExt = "androidx.test.ext:junit:${Versions.jUnitExt}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
    const val composeUiTestJUnit = "androidx.compose.ui:ui-test-junit4:${Versions.compose}"
    const val truth = "com.google.truth:truth:${Versions.truth}"
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
    const val versionCode = 1
    const val versionName = "0.0.1"
}
