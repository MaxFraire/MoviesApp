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
    const val lifecycle = "2.2.0"
    const val recyclerView = "1.1.0"
    const val flexbox = "2.0.1"
    const val dagger = "2.28"
    const val navigation = "2.3.2"
    const val retrofit = "2.9.0"
    const val httpProfiler = "1.0.7"
    const val coroutines = "1.4.2"
    const val paging = "3.0.0-alpha11"
    const val glide = "4.11.0"
    const val threeTenAbp = "1.3.0"
    const val timber = "4.7.1"
}

object Libs {
    const val androidGradlePlugin = "com.android.tools.build:gradle:${Versions.gradle}"

    const val kotlinStandardLibrary = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val kotlinExtensions = "org.jetbrains.kotlin:kotlin-android-extensions:${Versions.kotlin}"

    const val core = "androidx.core:core-ktx:${Versions.core}"
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val activity = "androidx.activity:activity-ktx:${Versions.activity}"
    const val fragment = "androidx.fragment:fragment-ktx:${Versions.fragment}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val flexbox = "com.google.android:flexbox:${Versions.flexbox}"
    const val lifeCycleExtensions = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
    const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
    const val lifecycleLiveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
    const val lifecycleViewModel =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"


    const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerView}"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"

    const val safeArgsPlugin =
        "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}"
    const val navigationUI = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    const val navigationFragment =
        "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"

    const val dagger = "com.google.dagger:dagger:${Versions.dagger}"
    const val daggerAndroid = "com.google.dagger:dagger-android:${Versions.dagger}"
    const val daggerAndroidSupport = "com.google.dagger:dagger-android-support:${Versions.dagger}"

    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitGsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val okHttpProfiler = "com.itkacher.okhttpprofiler:okhttpprofiler:${Versions.httpProfiler}"

    const val coroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    const val coroutinesCore =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"

    const val paging = "androidx.paging:paging-runtime:${Versions.paging}"

    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"

    const val threeTenAbp = "com.jakewharton.threetenabp:threetenabp:${Versions.threeTenAbp}"

    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
}

object AnnotationProcessors {
    const val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
    const val daggerAndroidProcessor =
        "com.google.dagger:dagger-android-processor:${Versions.dagger}"

    const val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glide}"
}


object Dependencies {
    private const val IMPLEMENTATION = "implementation"
    private const val KAPT = "kapt"

    val kotlin = HashMap<String, String>().apply {
        put(Libs.kotlinStandardLibrary, IMPLEMENTATION)
    }

    val androidx = HashMap<String, String>().apply {
        put(Libs.core, IMPLEMENTATION)
        put(Libs.appcompat, IMPLEMENTATION)
        put(Libs.fragment, IMPLEMENTATION)
        put(Libs.activity, IMPLEMENTATION)
        put(Libs.constraintLayout, IMPLEMENTATION)
        put(Libs.material, IMPLEMENTATION)
        put(Libs.recyclerView, IMPLEMENTATION)
        put(Libs.flexbox, IMPLEMENTATION)
    }

    val lifecycle = HashMap<String, String>().apply {
        put(Libs.lifeCycleExtensions, IMPLEMENTATION)
        put(Libs.lifecycleRuntime, IMPLEMENTATION)
        put(Libs.lifecycleLiveData, IMPLEMENTATION)
        put(Libs.lifecycleViewModel, IMPLEMENTATION)
    }

    val paging = HashMap<String, String>().apply {
        put(Libs.paging, IMPLEMENTATION)
    }

    val navigation = HashMap<String, String>().apply {
        put(Libs.navigationUI, IMPLEMENTATION)
        put(Libs.navigationFragment, IMPLEMENTATION)
    }

    val network = HashMap<String, String>().apply {
        put(Libs.retrofit, IMPLEMENTATION)
        put(Libs.retrofitGsonConverter, IMPLEMENTATION)
        put(Libs.okHttpProfiler, IMPLEMENTATION)
    }

    val coroutines = HashMap<String, String>().apply {
        put(Libs.coroutinesCore, IMPLEMENTATION)
        put(Libs.coroutinesAndroid, IMPLEMENTATION)
    }

    val dagger = HashMap<String, String>().apply {
        put(Libs.dagger, IMPLEMENTATION)
        put(AnnotationProcessors.daggerCompiler, KAPT)
        put(Libs.daggerAndroid, IMPLEMENTATION)
        put(Libs.daggerAndroidSupport, IMPLEMENTATION)
        put(AnnotationProcessors.daggerAndroidProcessor, KAPT)
    }

    val glide = HashMap<String, String>().apply {
        put(Libs.glide, IMPLEMENTATION)
        put(AnnotationProcessors.glideCompiler, KAPT)
    }

    val threeTenAbp = HashMap<String, String>().apply {
        put(Libs.threeTenAbp, IMPLEMENTATION)
    }

    val timber = HashMap<String, String>().apply {
        put(Libs.timber, IMPLEMENTATION)
    }
}

fun DependencyHandler.dependencies(map: HashMap<String, String>) {
    map.forEach { (key, value) ->
        add(value, key)
    }
}
