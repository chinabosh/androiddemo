package com.bosh.dependencies

class DependenciesWhiteListExtension {
    List<String> getDependenciesWhiteList() {
        return dependenciesWhiteList
    }
    public List<String> dependenciesWhiteList

    DependenciesWhiteListExtension() {
        init()
    }

    void init() {
        dependenciesWhiteList = new ArrayList<>()
        dependenciesWhiteList.add("androidx.test:runner:1.3.0-alpha03")
        dependenciesWhiteList.add("androidx.test.espresso:espresso-core:3.3.0-alpha03")
        dependenciesWhiteList.add("androidx.multidex:multidex-instrumentation:2.0.0")
        dependenciesWhiteList.add("androidx.appcompat:appcompat:1.0.0-beta01")
        dependenciesWhiteList.add("androidx.multidex:multidex:2.0.0")
        dependenciesWhiteList.add("androidx.constraintlayout:constraintlayout:1.1.3")
        dependenciesWhiteList.add("androidx.appcompat:appcompat:1.0.0-beta01")

        dependenciesWhiteList.add("com.google.android.material:material:1.2.0-alpha02")
        dependenciesWhiteList.add("com.android.tools.lint:lint-gradle:26.4.2")
        dependenciesWhiteList.add("junit:junit:4.12")

        dependenciesWhiteList.add("com.jakewharton:butterknife-compiler:10.1.0")
        dependenciesWhiteList.add("com.jakewharton:butterknife:10.1.0")

        dependenciesWhiteList.add("com.alibaba:arouter-compiler:1.2.2")

        dependenciesWhiteList.add("com.bosh.demo:library:0.0.6")
        dependenciesWhiteList.add("com.bosh.demo:library:0.0.10")
        dependenciesWhiteList.add("com.bosh.demo:flutter:0.0.2")
    }

}
