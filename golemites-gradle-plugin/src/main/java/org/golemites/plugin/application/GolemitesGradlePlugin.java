package org.golemites.plugin.application;

import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.Task;
import org.gradle.api.logging.LogLevel;
import org.golemites.api.GolemitesApplicationExtension;

public class GolemitesGradlePlugin implements Plugin<Project> {
    @Override
    public void apply(Project project) {
        project.getLogger().log(LogLevel.INFO,"Creating Golemites Deployment");
        GolemitesApplicationExtension ext = project.getExtensions().create("golemites", GolemitesApplicationExtension.class);
        project.getLogger().info("Created extension " + ext + " with " + ext.getRepository());
        Task installTask = project.getTasks().create( "install", InstallTask.class);
        Task deployTask = project.getTasks().create( "deploy", DeployTask.class);
        // make compileJava depend on generating sourcecode
        deployTask.dependsOn(installTask);
        project.getTasks().getByName("build").dependsOn(installTask);
    }
}