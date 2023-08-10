apply {
    from("$rootDir/base-module.gradle")
}
dependencies {
    "implementation"(project(":core:model"))
    "implementation"(project(":data:items_search"))

    "implementation"(libs.androidx.paging.compose)
    "implementation"(libs.androidx.paging.runtime)
}