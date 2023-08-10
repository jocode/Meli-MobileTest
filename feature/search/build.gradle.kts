apply {
    from("$rootDir/compose-module.gradle")
}

dependencies {
    "implementation"(libs.androidx.paging.compose)
    "implementation"(libs.androidx.paging.runtime)

    "implementation"(project(":core:common"))
    "implementation"(project(":core:ui"))
    "implementation"(project(":core:model"))
    "implementation"(project(":core:domain"))
}