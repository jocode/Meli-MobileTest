apply {
    from("$rootDir/base-module.gradle")
}

dependencies {
    "implementation"(libs.retrofit)
    "implementation"(libs.converter.moshi)
    "implementation"(libs.androidx.paging.runtime)

    "testImplementation"(project(":core:common"))
    "implementation"(project(":core:model"))
    "implementation"(project(":core:network"))
}