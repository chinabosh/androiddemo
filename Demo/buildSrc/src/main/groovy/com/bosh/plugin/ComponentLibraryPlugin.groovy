package com.bosh.plugin

import com.android.build.gradle.AppPlugin
import com.android.build.gradle.BaseExtension
import com.android.build.gradle.AppExtension
import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.LibraryPlugin
import com.android.build.gradle.internal.LoggerWrapper
import com.bosh.dependencies.DependenciesWhiteListExtension
import com.bosh.ext.ConfigExtension
import com.bosh.task.TestTask
import com.bosh.transform.GenerateRestrictTo
import com.bosh.transform.RestrictToTransform
import com.bosh.utils.Logger
import org.gradle.api.*
import org.gradle.api.artifacts.Dependency

class ComponentLibraryPlugin implements Plugin<Project> {
    @Override
    void apply(Project project) {
        Logger.make(project)
        Logger.i("plugin demo begin!")

        ConfigExtension configExtension = project.extensions.create("config", ConfigExtension.class)
        Logger.w("enableRestrictToTransform:" + configExtension.enableRestrictToTransform)


//        if (project.plugins.hasPlugin(AppPlugin) && configExtension.enableRestrictToTransform) {
//            Logger.w("registerTransform")
//            registerTransform(project)
//        }

        project.android {
            Logger.i("Arouter module name setting")
            defaultConfig {
                javaCompileOptions {
                    annotationProcessorOptions {
                        arguments = [AROUTER_MODULE_NAME: project.getName()]
                    }
                }
            }

            lintOptions {

                //should deal all warnings
                abortOnError true
                warningsAsErrors true

                // improve the priority of RestrictedApi
                enable "RestrictedApi"
                fatal "RestrictedApi"

                ignore "InvalidPackage"
            }
        }
        project.afterEvaluate {
            Logger.i('create task: hello')
            project.tasks.create("hello", TestTask.class, new Action<TestTask>() {
                @Override
                void execute(TestTask t) {
                    t.setMsg("external msg")
                    t.sayHello()
                    if (project.plugins.hasPlugin(AppPlugin) && configExtension.enableRestrictToTransform) {
                        Logger.w("registerTransform")
                        registerTransform(project)
                    }
                }
            })

            checkAndroid(project)
            if (project.hasProperty('android')) {

                project.android.compileOptions.sourceCompatibility = JavaVersion.VERSION_1_8
                project.android.compileOptions.targetCompatibility = JavaVersion.VERSION_1_8

                checkResourcePrefix(project)
//                AppExtension android = getAndroid(project)
//                android.sourceSets.each {set ->
//
//                }
                if (configExtension.enableRestrictToTransform) {
                    project.android.sourceSets.each { set ->
                        if ("main".equals(set.name)) {
                            GenerateRestrictTo.searchSourcePackage(set.java.srcDirs)
                        }
                    }
                }

//                DependenciesWhiteListExtension dwe = new DependenciesWhiteListExtension()
//                project.configurations.each {
//                    it.dependencies.each {
//                        if (it.group != null && it.name != null && it.version != null && !it.group.equals(project.rootProject.name)) {
//                            if (!dwe.dependenciesWhiteList.contains(it.group + ":" + it.name + ":" + it.version)) {
//                                throw new GradleException("project:" + project.name + "," + "the dependency:\"" + it.group + ":" + it.name + ":" + it.version + "\" don't in the white list!")
//                            }
//                        }
//                    }
//                }

            }
        }
    }

    /**
     * require that all library module resource must have prefix!
     * @param project
     */
    private static void checkResourcePrefix(Project project) {
        def android
        if (project.plugins.hasPlugin(LibraryPlugin)) {
            android = getLibrary(project)
        } else if (project.plugins.hasPlugin(AppPlugin)) {
            android = getAndroid(project)
        } else {
            Logger.i("there has no android plugin")
            return
        }
        String prefix = android.resourcePrefix
        if (!prefix?.trim()) {
            throw new GradleException('module must have resourcePrefix at \'build.gradle\',like:\n' +
                    'android {\n' +
                    '    defaultConfig {\n' +
                    '        ...\n' +
                    '        resourcePrefix \'[module_name]_\'\n' +
                    '    }\n' +
                    '}\n')
        }
    }

    private static AppExtension getAndroid(Project project) {
        return project.getExtensions().getByType(AppExtension.class)
    }

    private static LibraryExtension getLibrary(Project project) {
        return project.getExtensions().findByType(LibraryExtension.class)
    }

    private static void checkAndroid(Project project) {
        if (!project.android) {
            throw new GradleException('must apply from \"com.android.application\" or \"com.android.library\" first')
        }
    }

    private static void registerTransform(Project project) {
        getAndroid(project).registerTransform(new RestrictToTransform(project))
    }
}
