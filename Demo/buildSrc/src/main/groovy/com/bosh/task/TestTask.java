package com.bosh.task;

import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.InputFile;
import org.gradle.api.tasks.InputFiles;
import org.gradle.api.tasks.TaskAction;

public class TestTask extends DefaultTask {

    private String msg;

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @TaskAction
    public void sayHello() {
        System.out.println("hello world,this is test task!" + "msg:" + msg);
    }

    @InputFiles
    @InputFile
    @Input
    public void getInput(){

    }
}
