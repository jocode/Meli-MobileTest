apply {
    from("$rootDir/compose-module.gradle")
}

dependencies {
    "implementation"(project(":core:model"))
}
