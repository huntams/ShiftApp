pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "ShiftApp"
include(":app")
include(":core:data")
include(":core:network")
include(":features:userlist")
include(":features:userdetails")
include(":core:model")
include(":core:domain")
