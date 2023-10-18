plugins {
    java
}

group = "net.tonimatasdev"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
}

tasks.withType<Jar> {
    manifest.attributes(
        "Main-Class" to "dev.tonimatas.plorix.Main"
    )
}