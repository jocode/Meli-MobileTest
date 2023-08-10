apply {
    from("$rootDir/compose-module.gradle")
}

dependencies {
    "implementation"(project(":core:common"))
    "implementation"(project(":core:ui"))
    "implementation"(project(":core:model"))
    "implementation"(project(":core:domain"))
    "testImplementation"(project(":core:common"))
}