import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.compose)
    alias(libs.plugins.cocoapods)
    alias(libs.plugins.android.application)
    alias(libs.plugins.libres)
    alias(libs.plugins.buildConfig)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.apollo)
//    id("com.squareup.sqldelight")
    //1
    id("app.cash.sqldelight")
    //moko for share resources
    id("dev.icerock.mobile.multiplatform-resources")

}
//Dependencies versions
val coroutinesVersion = "1.6.4"
val ktorVersion = "2.2.1"
val koinVersion = "3.3.2"
@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    targetHierarchy.default()
    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "17"
            }
        }
    }

    jvm("desktop")

    js {
        browser()
        binaries.executable()
    }


        iosX64()
        iosArm64()
        iosSimulatorArm64()




    cocoapods {
        version = "1.0.0"
        summary = "Compose application framework"
        homepage = "empty"
        ios.deploymentTarget = "11.0"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "ComposeApp"
            isStatic = true
            //here you can add any required exports
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)
                implementation(compose.material3)
                implementation(libs.libres)
                implementation(libs.voyager.navigator)
                implementation(libs.composeImageLoader)
                implementation(libs.napier)
                implementation(libs.kotlinx.coroutines.core)
                implementation(libs.insetsx)
                implementation(libs.ktor.core)
                implementation(libs.composeIcons.featherIcons)
                implementation(libs.kotlinx.serialization.json)
                implementation(libs.kotlinx.datetime)
                implementation(libs.multiplatformSettings)
                implementation(libs.koin.core)
                implementation(libs.kstore)
                implementation(libs.apollo.runtime)
                implementation(compose.materialIconsExtended)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)
                implementation(compose.ui)

                //ktor
                implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
                implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
                implementation(libs.ktor.serilization)
                implementation(libs.ktor.logging)
                implementation(libs.logback.logback)


                implementation(kotlin("stdlib-common"))
                implementation("co.touchlab:kermit:2.0.0-RC4") //Add latest version

//                implementation("com.squareup.sqldelight:runtime:1.5.5")
//                implementation("com.squareup.sqldelight:coroutines-extensions:1.5.5")
                implementation("app.cash.sqldelight:coroutines-extensions:2.0.0-alpha05")
                implementation("app.cash.sqldelight:primitive-adapters:2.0.0-alpha05")

                implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0")

                val voyagerVersion = "1.0.0-rc05"
                // Navigator
                implementation("cafe.adriel.voyager:voyager-navigator:$voyagerVersion")
//                implementation("cafe.adriel.voyager:voyager-bottom-sheet-navigator:$voyagerVersion")
//                implementation("cafe.adriel.voyager:voyager-tab-navigator:$voyagerVersion")
//                implementation("cafe.adriel.voyager:voyager-transitions:$voyagerVersion")

                // Koin integration
                implementation("cafe.adriel.voyager:voyager-koin:$voyagerVersion")

                //moko for share resources
                api("dev.icerock.moko:resources:0.23.0")
                api("dev.icerock.moko:resources-compose:0.23.0") // for compose multiplatform
                implementation("dev.icerock.moko:resources-test:0.23.0")


            }
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation("dev.icerock.moko:resources-test:0.23.0")
            }
        }

        val androidMain by getting {
            dependencies {
                implementation(libs.androidx.appcompat)
                implementation(libs.androidx.activityCompose)
                implementation(libs.compose.uitooling)
                implementation(libs.kotlinx.coroutines.android)
                implementation(libs.ktor.client.okhttp)
                implementation ("androidx.media:media:1.1.0")
                implementation("app.cash.sqldelight:android-driver:2.0.0-alpha05")
//                implementation("com.squareup.sqldelight:android-driver:1.5.5")
                api("io.insert-koin:koin-android:$koinVersion")
            }
        }

        val desktopMain by getting {
            dependencies {
                implementation(compose.desktop.common)
                implementation(compose.desktop.currentOs)
                implementation(libs.ktor.client.okhttp)
                implementation("app.cash.sqldelight:sqlite-driver:2.0.0-alpha05")
            }
        }

        val jsMain by getting {
            dependencies {
                implementation(compose.web.core)
            }
        }
        val androidUnitTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting

        val iosMain by getting {
            dependencies {
                implementation(libs.ktor.client.darwin)

//                implementation("com.squareup.sqldelight:native-driver:1.5.5")
                implementation("app.cash.sqldelight:native-driver:2.0.0-alpha05")
            }

            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }

        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by getting {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }

    }
}

android {
    namespace = "com.moali.eqraa"
    compileSdk = 33

    defaultConfig {
        minSdk = 21
        targetSdk = 33

        applicationId = "com.moali.eqraa.androidApp"
        versionCode = 1
        versionName = "1.0.0"
    }
    sourceSets["main"].apply {
        manifest.srcFile("src/androidMain/AndroidManifest.xml")
//        res.srcDirs(
//            listOf(
//                "src/androidMain/resources",
//                "src/commonMain/resources" // <-- add the commonMain Resources
//            )
//        )
    }
    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(17))
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    sourceSets["main"].resources.setSrcDirs(
        listOf(
            "src/androidMain/resources",
            "src/commonMain/resources" // <-- add the commonMain Resources
        )
    )
    packagingOptions {
        resources.excludes.add("META-INF/**")
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "com.moali.eqraa.desktopApp"
            packageVersion = "1.0.0"
        }
    }
}

compose.experimental {
    web.application {}
}


tasks.getByPath("desktopProcessResources").dependsOn("libresGenerateResources")
tasks.getByPath("desktopSourcesJar").dependsOn("libresGenerateResources")
tasks.getByPath("jsProcessResources").dependsOn("libresGenerateResources")


multiplatformResources {
    multiplatformResourcesPackage = "com.moali.kmm_sharingresources"
    multiplatformResourcesClassName = "SharedRes"
}

dependencies {
//    implementation("androidx.core:core:1.10.1")
//    implementation(libs.androidx.ui.android)
//    implementation(project(mapOf("path" to ":androidApp")))
    commonMainApi("dev.icerock.moko:mvvm-core:0.16.1")
    commonMainApi("dev.icerock.moko:mvvm-compose:0.16.1")
    commonMainApi("dev.icerock.moko:mvvm-flow:0.16.1")
    commonMainApi("dev.icerock.moko:mvvm-flow-compose:0.16.1")
}

sqldelight {
    databases{
        create("EqraaDatabase") {
            packageName.set("com.moali.eqraa.database")

        }
    }
}

apollo {
    service("api") {
        // GraphQL configuration here.
        // https://www.apollographql.com/docs/kotlin/advanced/plugin-configuration/
        packageName.set("com.moali.eqraa.graphql")
    }
}
