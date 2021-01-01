plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    compileSdkVersion(AppConfig.compileSdk)
    buildToolsVersion(AppConfig.buildToolVersion)

    defaultConfig {
        applicationId = "com.maxfraire.movies"
        minSdkVersion(AppConfig.minSdk)
        targetSdkVersion(AppConfig.targetSdk)
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName

        buildConfigField("String", "TMDB_API_KEY","\"" + project.properties["MOVIES_APP_TMDB_API_KEY"] + "\"")

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    buildFeatures {
        dataBinding = true
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    dependencies(Dependencies.kotlin)
    dependencies(Dependencies.androidx)
    dependencies(Dependencies.lifecycle)
    dependencies(Dependencies.navigation)
    dependencies(Dependencies.paging)
    dependencies(Dependencies.network)
    dependencies(Dependencies.dagger)
    dependencies(Dependencies.coroutines)
    dependencies(Dependencies.glide)
    dependencies(Dependencies.threeTenAbp)
    dependencies(Dependencies.timber)
}