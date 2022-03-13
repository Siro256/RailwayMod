plugins {
    //Latest version: https://fabricmc.net/develop/
    id("fabric-loom") version "0.11-SNAPSHOT"
}

group = rootProject.group.toString() + ".core"

dependencies {
    implementation(kotlin("stdlib"))

    //Minecraft API, using fabric's mapping
    //Latest version: https://fabricmc.net/develop/
    add("minecraft", "com.mojang:minecraft:1.18.2")
    add("mappings", loom.officialMojangMappings())
}

tasks {
    }

    getByName("compileKotlin", KotlinCompile::class) {
        destinationDirectory.set(File(buildDir, "classes/java/main"))

        dependsOn("replaceValuePlaceholder")
    }

    //Gradle didn't create jar by task "jar"
    create("createLibraryJar", Jar::class) {
        archiveBaseName.set(project.name)
    }

    getByName("jar").dependsOn(getByName("createLibraryJar"))
}
