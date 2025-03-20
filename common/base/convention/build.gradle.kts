plugins {
    `java-gradle-plugin`
    `maven-publish`
}

group = "kz.rymbek.platform.common.base"
version = "1.0.0"

publishing {
    repositories {
        maven {
            url = uri(layout.buildDirectory.dir("repo"))
        }
    }
}