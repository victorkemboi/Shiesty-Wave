plugins {
    id(BuildPlugins.androidApplication)
    id(BuildPlugins.kotlinAndroid)
}

android {
    compileSdk = 30
    buildToolsVersion = "30.0.3"

    defaultConfig {
        applicationId = "com.mes.shiestywave"
        minSdk = 21
        targetSdk = 30
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
        useIR = true
        freeCompilerArgs = listOf(
            "-Xallow-unstable-dependencies"
        )
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose
    }
    dynamicFeatures.addAll(
        mutableSetOf(":onboarding")
    )
}

dependencies {

    api(project(BuildModules.dataModule))
    api(project(BuildModules.domainModule))

    implementation(Libraries.coreKtx)
    implementation(Libraries.appCompat)
    implementation(Libraries.material)
    implementation(Libraries.materialIcons)
    implementation(Libraries.composeUi)
    implementation(Libraries.composeMaterial)
    implementation(Libraries.composeTooling)
    implementation(Libraries.lifecycleRuntimeKtx)
    implementation(Libraries.composeActivity)
    implementation(Libraries.composeNavigation)
    implementation(Libraries.pagingCompose)

    // DI - KOIN
    implementation(Libraries.koin)
    implementation(Libraries.koinCompose)


    implementation(Libraries.ConstraintLayoutCompose)

    implementation(Libraries.accompanist)
    implementation(Libraries.swipeRefresh)
    implementation(Libraries.accompanistSystemUiController)

    implementation(Libraries.timber)
    implementation(Libraries.joda)
    implementation(Libraries.glide)
    implementation(Libraries.palette)

    testImplementation(TestLibraries.jUnit)
    androidTestImplementation(TestLibraries.jUnitExt)
    androidTestImplementation(TestLibraries.espressoCore)
    androidTestImplementation(TestLibraries.composeUiTestJUnit)
}
