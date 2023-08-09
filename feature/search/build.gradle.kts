apply {
    from("$rootDir/compose-module.gradle")
}

dependencies {
    "implementation"(project(":core:common"))
    "implementation"(project(":core:network"))
    "implementation"(project(":core:ui"))
    "implementation"(project(":core:model"))
    "implementation"(project(":core:domain"))
}