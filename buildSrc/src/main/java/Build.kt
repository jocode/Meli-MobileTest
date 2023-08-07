object Build {
    const val kotlinVersion = "1.8.20"

    const val androidBuildToolsVersion = "8.0.2"
    const val androidBuildTools = "com.android.tools.build:gradle:$androidBuildToolsVersion"

    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${kotlinVersion}"

    private const val hiltAndroidGradlePluginVersion = "2.46"
    const val hiltAndroidGradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:$hiltAndroidGradlePluginVersion"
}