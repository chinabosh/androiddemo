package com.bosh.transform

import androidx.annotation.RestrictTo
import com.bosh.utils.Logger
import javassist.ClassPool
import javassist.CtClass
import javassist.CtField
import javassist.CtMethod
import javassist.NotFoundException
import javassist.bytecode.AnnotationsAttribute
import javassist.bytecode.ClassFile
import javassist.bytecode.ConstPool
import javassist.bytecode.annotation.Annotation
import javassist.bytecode.annotation.EnumMemberValue
import org.gradle.api.Project

import java.lang.reflect.Modifier

class GenerateRestrictTo {
    static ClassPool classPool = ClassPool.getDefault()


    static void generate(File dir, Project project) {
        classPool.appendClassPath(dir.absolutePath)
//        Logger.w("bootClasspath:" + project.android.bootClasspath[0].toString())
        classPool.appendClassPath(project.android.bootClasspath[0].toString())
        classPool.importPackage("androidx.annotation.RestrictTo")
        classPool.importPackage("android.os.Bundle")
        if (dir.isDirectory()) {
            dir.eachFileRecurse { file ->
                doGenerate(file, dir.absolutePath)
            }
        }
    }

    static void doGenerate(File file, String path) {
        def name = file.absolutePath
        if (name.endsWith(".class")) {
//            Logger.w("class name:" + name)
            try {
                int index = name.indexOf("com/")
                if (index > -1) {
                    name = name.substring(index, name.length() - 6)
                    name = name.replaceAll("/", ".")
                    CtClass cc = classPool.getCtClass(name)
                    def checkResult = !check(cc)
                    if (checkResult) {
                        return
                    }
                    generate(cc, path)
                    Logger.w("generate absolute path:" + file.absolutePath)
                }
            } catch (NotFoundException e) {
//                        Logger.e("RestrictTo not found:" + e.getMessage())
            }
        } else {
//            Logger.w("not endsWith .class:" + name)
        }
    }

    static boolean check(CtClass ctClass) {
        def res = false
        String name = ctClass.name
        if (name.contains("bosh") && !name.contains("R\$") && !name.contains("R2\$")) {
            String className = name.substring(name.lastIndexOf(".") + 1)
            if ("R".equals(className) || "BuildConfig".equals(className) || className.contains("Manifest")) {
                res = false
            } else {
                if (ctClass.annotations == null) {
                    res =  true
                } else {
                    res = true
                    ctClass.annotations.each { Object object ->
                        if (object instanceof java.lang.annotation.Annotation) {
                            def typeName = object.annotationType().typeName
                            if (typeName.contains("com.china.bosh.mylibrary.annotation")
                                    || typeName.contains("androidx.annotation.RestrictTo")) {
                                Logger.w("typeName:" + typeName)
                                res =  false
                            }
                        }
                    }
                }
            }

        }
        return res
    }

    static void generate(CtClass cc, String path) {
        def name = cc.name
        Logger.w("generate RestrictTo to before:" + name)
        if (cc.frozen) {
            cc.defrost()
        }
        ClassFile classFile = cc.classFile
        ConstPool constPool = classFile.constPool

        AnnotationsAttribute annotationsAttribute = new AnnotationsAttribute(constPool, AnnotationsAttribute.visibleTag)
        Annotation annotation = new Annotation("androidx.annotation.RestrictTo", constPool)
//        Logger.w("generate RestrictTo to before1:" + name)
        EnumMemberValue enumMemberValue = new EnumMemberValue(constPool)
//        Logger.w("generate RestrictTo to before2:" + name)
        enumMemberValue.setType("androidx.annotation.RestrictTo\$Scope")
//        Logger.w("generate RestrictTo to before3:" + name)
        enumMemberValue.setValue('LIBRARY')
//        Logger.w("generate RestrictTo to before4:" + name)
        annotation.addMemberValue("value", enumMemberValue)
//        Logger.w("generate RestrictTo to before5:" + name)
        annotationsAttribute.addAnnotation(annotation)
//        Logger.w("generate RestrictTo to before6:" + name)
        classFile.addAttribute(annotationsAttribute)

//        if ("com.china.bosh.demo.Test".equals(name)) {
//            CtField ctField = new CtField(CtClass.booleanType, "isTest", cc)
//            ctField.setModifiers(Modifier.PUBLIC)
//            cc.addField(ctField)
//        }

//        cc.annotations().each {Object object ->
//            Logger.w("annotation name:" + object.getClass().name)
//        }
//        Logger.w("class byte code :" + cc.toString())
        cc.writeFile(path)
        cc.detach()
    }
}
