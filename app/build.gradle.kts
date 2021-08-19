import nu.studer.gradle.jooq.JooqGenerate

object Configurer {
    const val databaseDriver = "org.postgresql.Driver"
    const val databaseUrl = "jdbc:postgresql://127.0.0.1:15432/avocado_dev?schema=public"
    const val databaseUser = "avocado"
    const val databasePassword = "test1234"
    const val databaseDialect = "org.jooq.meta.postgres.PostgresDatabase"
    const val generatorName = "org.jooq.codegen.KotlinGenerator"
    const val generateStrategy = "org.jooq.codegen.DefaultGeneratorStrategy"
}

plugins {
    // --#Gradle
    id("nu.studer.jooq")
//    id("org.jlleitschuh.gradle.ktlint")

    id("org.springframework.boot")
    id("io.spring.dependency-management")
    id("org.jetbrains.kotlin.plugin.spring")

    // --#Project
    id("conventions.spring.kotlin-application")
}

jooq {
    version.set(Versions.jooq)
    edition.set(nu.studer.gradle.jooq.JooqEdition.OSS)
    configurations {
        create("main") {
            generateSchemaSourceOnCompilation.set(true)
            jooqConfiguration.apply {
                logging = org.jooq.meta.jaxb.Logging.INFO
                jdbc.apply {
                    driver = Configurer.databaseDriver
                    url = Configurer.databaseUrl
                    user = Configurer.databaseUser
                    password = Configurer.databasePassword
                }
                generator.apply {
                    name = Configurer.generatorName
                    strategy.apply {
                        name = Configurer.generateStrategy
                    }
                    database.apply {
                        name = Configurer.databaseDialect
                        inputSchema = "public"
                    }
                    generate.apply {
                        isDeprecated = false
                        isRecords = false
                        isRelations = true
                        isImmutablePojos = true
                        isFluentSetters = true
                        isPojosEqualsAndHashCode = true
                    }
                    target.apply {
                        directory = "build/generated/jooq"
                        packageName = "io.wisoft.exercise"
                    }
                }
            }
        }
    }
}

tasks.named<JooqGenerate>("generateJooq") {
    allInputsDeclared.set(true)
}
