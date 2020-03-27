// Declaration of the Gradle extension to use
plugins {
    java
    application
    /*
     * Adds tasks to export a runnable jar.
     * In order to create it, launch the "shadowJar" task.
     * The runnable jar will be found in build/libs/projectname-all.jar
     */
    id("com.github.johnrengelman.shadow") version "5.2.0"
}
repositories {
    jcenter() // Contains the whole Maven Central + other stuff
    mavenCentral()
	maven(url = "https://nexus.gluonhq.com/nexus/content/repositories/releases/")
}
// List of JavaFX modules you need. Comment out things you are not using.
val javaFXModules = listOf(
    "base",
    "controls",
    "fxml",
    "swing",
    "graphics"
)
// All required for OOP
val supportedPlatforms = listOf("linux", "mac", "win")

dependencies {
    // Example library: Guava. Add what you need (and remove Guava if you don't use it)
    implementation("com.google.guava:guava:28.1-jre")
    // JavaFX: comment out if you do not need them
    for (platform in supportedPlatforms) {
        for (module in javaFXModules) {
            implementation("org.openjfx:javafx-$module:13:$platform")
        }
    }
    // JUnit 4
    implementation("junit:junit:4.13")
    // FXGL
    implementation("com.github.almasb:fxgl:11.8")
}

application {
    mainClassName = "application.Launcher"
}
