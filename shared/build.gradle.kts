plugins {
    kotlin("multiplatform")
    id("com.android.library")
}

object Versions {
    const val kotlinxCoroutines = "1.6.4"
    const val koin = "3.2.0"
    const val okio = "3.3.0"
    const val kotlinxDatetime = "0.4.0"
}

kotlin {
    android()
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {

                // Kotlin datetime
                implementation("org.jetbrains.kotlinx:kotlinx-datetime:${Versions.kotlinxDatetime}")

                // Kotlin coroutines
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinxCoroutines}")

                // Koin
                implementation("io.insert-koin:koin-core:${Versions.koin}")
                implementation("io.insert-koin:koin-android:${Versions.koin}")

                // Okio
                implementation("com.squareup.okio:okio:${Versions.okio}")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation("io.insert-koin:koin-test:${Versions.koin}")
            }
        }
        val androidMain by getting
        val androidTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }
}

android {
    namespace = "org.asatger.kodoist"
    compileSdk = 32
    defaultConfig {
        minSdk = 22
        targetSdk = 32
    }
}