pluginManagement {
    val jooqPluginVersion: String by settings
    val kotlinVersion: String by settings
    val ktLintPluginVersion: String by settings

    val springBootPluginVersion: String by settings
    val springDependencyManagementPluginVersion: String by settings

    val asciiDoctorJvmConvertPluginVersion: String by settings
    val restdocsApiSpecPluginVersion: String by settings

    plugins {
        id("nu.studer.jooq") version jooqPluginVersion

        id("org.jetbrains.kotlin.plugin.spring") version kotlinVersion
        id("org.jlleitschuh.gradle.ktlint") version ktLintPluginVersion

        id("org.springframework.boot") version springBootPluginVersion
        id("io.spring.dependency-management") version springDependencyManagementPluginVersion

        id("org.asciidoctor.jvm.convert") version asciiDoctorJvmConvertPluginVersion
        id("com.epages.restdocs-api-spec") version restdocsApiSpecPluginVersion
    }
}

rootProject.name = "r2dbc-jooq"
include("app")
