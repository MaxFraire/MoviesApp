import org.gradle.api.artifacts.dsl.DependencyHandler

object AppConfig {
    const val compileSdk = 29
    const val buildToolVersion = "30.0.2"
    const val minSdk = 23
    const val targetSdk = 29
    const val versionCode = 1
    const val versionName = "1.0.0"
}

object Versions {
    const val gradle = "4.0.1"
    const val kotlin = "1.3.72"

    const val core = "1.3.0"
    const val appcompat = "1.1.0"
    const val material = "1.3.0-alpha01"
    const val constraintLayout = "1.1.3"
    const val activity = "1.1.0"
    const val fragment = "1.2.5"
    const val recyclerView = "1.1.0"
    const val dagger = "2.28"

    /* test */
    const val junit = "4.12"
}

object Libs {
    const val kotlinStandarLibrary = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    const val core = "androidx.core:core-ktx:${Versions.core}"
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val activity = "androidx.activity:activity-ktx:${Versions.activity}"
    const val fragment = "androidx.fragment:fragment-ktx:${Versions.fragment}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerView}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"

    const val dagger = "com.google.dagger:dagger:${Versions.dagger}"
    const val daggerAndroid = "com.google.dagger:dagger-android:${Versions.dagger}"
    const val daggerAndroidSupport = "com.google.dagger:dagger-android-support:${Versions.dagger}"

}

object Dependencies {
    private const val IMPLEMENTATION = "implementation"
    private const val KAPT = "kapt"

    val androidCore = HashMap<String, String>().apply {
        put(Libs.kotlinStandarLibrary, IMPLEMENTATION)
        put(Libs.core, IMPLEMENTATION)
        put(Libs.dagger, IMPLEMENTATION)
        put(AnnotationProcessors.daggerCompiler, KAPT)
    }

    val androidUI = HashMap<String, String>().apply {
        put(Libs.appcompat, IMPLEMENTATION)
        put(Libs.constraintLayout, IMPLEMENTATION)
        put(Libs.material, IMPLEMENTATION)
        put(Libs.fragment, IMPLEMENTATION)

        put(Libs.daggerAndroid, IMPLEMENTATION)
        put(Libs.daggerAndroidSupport, IMPLEMENTATION)
        put(AnnotationProcessors.daggerAndroidProcessor, KAPT)
    }

}

object AnnotationProcessors {
    const val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
    const val daggerAndroidProcessor = "com.google.dagger:dagger-android-processor:${Versions.dagger}"
}

object TestLibs {
    const val junit = "junit:junit:${Versions.junit}"
}

fun DependencyHandler.dependencies(map: HashMap<String, String>) {
    map.forEach { (key, value) ->
        add(value, key)
    }
}
