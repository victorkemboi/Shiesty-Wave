
plugins {
    id(BuildPlugins.androidLibrary)
    id(BuildPlugins.kotlinAndroid)
    id(BuildPlugins.kotlinParcelizePlugin)
    id(BuildPlugins.kapt)
}
android {

    compileSdk = AndroidSdk.compileSdkVersion
    buildToolsVersion = AndroidSdk.buildToolsVersion

    defaultConfig {
        minSdk = AndroidSdk.minSdkVersion
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    testOptions {
        animationsDisabled = true
        unitTests.apply {
            isReturnDefaultValues = true
            isIncludeAndroidResources = false
        }
    }

    buildFeatures {
        compose = true
    }

    kotlinOptions {
        jvmTarget = "1.8"
        useIR = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose
    }

    buildTypes {

        getByName("release") {
            isMinifyEnabled = false
            proguardFiles("proguard-android.txt", "proguard-rules.pro")
        }
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(Libraries.coreKtx)

    // DI - KOIN
    implementation(Libraries.koin)

    // Joda
    implementation(Libraries.joda)

    implementation(Libraries.pagingCompose)

    // Room DB and Store
    implementation(Libraries.room)
    implementation(Libraries.roomRuntime)
    kapt(Libraries.roomCompiler)

    // tests
    testImplementation(TestLibraries.jUnit)
    testImplementation(TestLibraries.truth)
}
