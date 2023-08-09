apply {
    from("$rootDir/base-module.gradle")
}

dependencies {
    "implementation"(libs.retrofit)
    "implementation"(libs.converter.moshi)

    "implementation"(project(":core:model"))
    "implementation"(project(":core:network"))
}