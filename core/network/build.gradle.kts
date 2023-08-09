apply {
    from("$rootDir/base-module.gradle")
}

dependencies {
    "implementation"(libs.okhttp)
    "implementation"(libs.okhttp.logging.interceptor)
    "implementation"(libs.retrofit)
    "implementation"(libs.converter.moshi)

    "implementation"(project(":core:model"))
}