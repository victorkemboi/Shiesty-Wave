plugins {
    id(BuildPlugins.dynamicFeature)
    id(BuildPlugins.kotlinAndroid)
}
android {
    compileSdk = AndroidSdk.compileSdkVersion
    buildToolsVersion = AndroidSdk.buildToolsVersion

    defaultConfig {
        applicationId = "com.mes.shiestywave.onboarding"
        minSdk = AndroidSdk.minSdkVersion
        targetSdk = AndroidSdk.targetSdkVersion
        versionCode = AndroidSdk.versionCode
        versionName = AndroidSdk.versionName

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
}

dependencies {
    implementation(project(BuildModules.appModule))
    implementation(Libraries.coreKtx)

    // Test libraries
    testImplementation(TestLibraries.jUnit)
    androidTestImplementation(TestLibraries.jUnitExt)
    androidTestImplementation(TestLibraries.espressoCore)
    androidTestImplementation(TestLibraries.composeUiTestJUnit)
}
