package com.bosh.transform


import com.bosh.utils.Logger
import javassist.ClassPool
import javassist.CtClass
import javassist.NotFoundException
import javassist.bytecode.AnnotationsAttribute
import javassist.bytecode.ClassFile
import javassist.bytecode.ConstPool
import javassist.bytecode.annotation.Annotation
import javassist.bytecode.annotation.EnumMemberValue
import org.apache.commons.io.IOUtils
import org.gradle.api.Project

import java.util.jar.JarEntry
import java.util.jar.JarFile
import java.util.jar.JarOutputStream
import java.util.zip.ZipEntry

class GenerateRestrictTo {
    static ClassPool classPool = ClassPool.getDefault()

    public static ArrayList<String> sourceDirs = new ArrayList<>()

    /**
     * 搜索需要添加代码的包路径，如：com/company/project
     * @param fileSet
     */
    static void searchSourcePackage(Set<File> fileSet) {
        if (fileSet == null || fileSet.size() <= 0) {
            return
        }
        sourceDirs.clear()
        fileSet.each { file ->
            getPackagePath(file, 0, new StringBuilder())
        }
        sourceDirs.each {
            Logger.i("source package:" + it)
        }
    }

    /**
     *
     * @param file
     * @param depth
     * @param result
     */
    private static void getPackagePath(File file, int depth, StringBuilder result) {
        //搜索3层目录，一般为com/company/project
//        if (depth >= 3) {
//            def res = result.toString()
//            if (!"".equals(res) && !sourceDirs.contains(res)) {
//                sourceDirs.add(res)
//            }
//            return
//        }
        boolean isDir = false
        file.eachDir { subFile ->
            if (depth != 0) {
                result.append(File.separator)
            }
            result.append(subFile.name)
            getPackagePath(subFile, depth + 1, new StringBuilder(result))
            isDir = true
        }
        if (!isDir) {
            def res = result.toString()
            if (!"".equals(res) && !sourceDirs.contains(res)) {
                sourceDirs.add(res)
            }
        }
    }


    static void generate(File dir, Project project) {
        classPool.appendClassPath(dir.absolutePath)
        classPool.appendClassPath(project.android.bootClasspath[0].toString())//android.jar
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
                def index = -1
                sourceDirs.each {
                    def tmp = name.indexOf(it)
                    if (tmp > -1) {
                        index = tmp
                    }
                }
                if (index > -1) {
                    name = name.substring(index, name.length() - 6)
                    name = name.replaceAll(File.separator, ".")

                    def checkResult = !checkResourceAndRepeat(name)
                    if (checkResult) {
                        return
                    }
                    CtClass cc = classPool.getCtClass(name)
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

    static boolean checkResourceAndRepeat(String name) {
        def res = false
        CtClass ctClass = classPool.get(name)
        boolean isInSource = false
        for (item in sourceDirs) {
            if (name.contains(item.replace(File.separator, "."))) {
                isInSource = true
                break
            }
        }
        if (isInSource) {
            String className = name.substring(name.lastIndexOf(".") + 1)
            //资源文件生成的类不处理,butterknife生成的类不处理
            if ("R".equals(className)
                    || "BuildConfig".equals(className)
                    || className.contains("Manifest")
                    || name.contains("R\$")
                    || name.contains("R2\$")
                    || name.contains("ViewBinding")) {
                res = false
            } else {
                try {
                    if (ctClass.annotations == null) {
                        res = true
                    } else {
                        res = true
                        ctClass.annotations.each { Object object ->
                            if (object instanceof java.lang.annotation.Annotation) {
                                def typeName = object.annotationType().typeName
                                //有@Open,或者已经加上@RestrictTo的不处理
                                if (typeName.contains("com.china.bosh.mylibrary.annotation.Open")
                                        || typeName.contains("androidx.annotation.RestrictTo")) {
                                    Logger.i("typeName:" + typeName)
                                    res = false
                                }
                            }
                        }
                    }
                } catch (ClassNotFoundException e) {
                    res = false
                }

            }

        }
        return res
    }

    static generate(CtClass cc, String path) {
        def name = cc.name
        Logger.w("generate RestrictTo to before:" + name)
        cc = modifyClass(cc)
        cc.writeFile(path)
        cc.detach()
    }

    static byte[] generate(CtClass cc) {
        def bytes
        def name = cc.name
        Logger.w("generate RestrictTo to before:" + name)
        if (cc.frozen) {
            cc.defrost()
        }
        cc = modifyClass(cc)
        bytes = cc.toBytecode()
        cc.detach()
        return bytes
    }

    private static CtClass modifyClass(CtClass cc) {
        if (cc.frozen) {
            cc.defrost()
        }
        ClassFile classFile = cc.classFile
        ConstPool constPool = classFile.constPool

        AnnotationsAttribute annotationsAttribute = new AnnotationsAttribute(constPool, AnnotationsAttribute.visibleTag)
        Annotation annotation = new Annotation("androidx.annotation.RestrictTo", constPool)
        EnumMemberValue enumMemberValue = new EnumMemberValue(constPool)
        enumMemberValue.setType("androidx.annotation.RestrictTo\$Scope")
        enumMemberValue.setValue('LIBRARY')
        annotation.addMemberValue("value", enumMemberValue)
        annotationsAttribute.addAnnotation(annotation)
        classFile.addAttribute(annotationsAttribute)
        return cc
    }

    static File insertRestrictCodeIntoJarFile(File jarFile, Project project) {
        classPool.appendClassPath(jarFile.absolutePath)
        classPool.appendClassPath(project.android.bootClasspath[0].toString())//android.jar
        classPool.importPackage("androidx.annotation.RestrictTo")
        classPool.importPackage("java.lang.annotation")
        if (jarFile) {
            def optJar = new File(jarFile.getParent(), jarFile.name + ".opt")
            if (optJar.exists())
                optJar.delete()
            def file = new JarFile(jarFile)
            Enumeration enumeration = file.entries()
            JarOutputStream jarOutputStream = new JarOutputStream(new FileOutputStream(optJar))

            while (enumeration.hasMoreElements()) {
                JarEntry jarEntry = (JarEntry) enumeration.nextElement()
                String entryName = jarEntry.getName()
                ZipEntry zipEntry = new ZipEntry(entryName)
                InputStream inputStream = file.getInputStream(jarEntry)
                jarOutputStream.putNextEntry(zipEntry)
//                Logger.w("entryName:" + entryName)
                String name = entryName.replaceAll(File.separator, ".").replace(".class", "")
                if (checkResourceAndRepeat(name)) {
                    def cc = classPool.get(name)
                    def bytes = generate(cc)
                    if (bytes != null) {
                        Logger.i('Insert RestrictTo code to class >> ' + entryName)
                        jarOutputStream.write(bytes)
                    } else {
                        jarOutputStream.write(IOUtils.toByteArray(inputStream))
                    }
                } else {
                    jarOutputStream.write(IOUtils.toByteArray(inputStream))
                }
                inputStream.close()
                jarOutputStream.closeEntry()
            }
            jarOutputStream.close()
            file.close()

            if (jarFile.exists()) {
                jarFile.delete()
            }
            optJar.renameTo(jarFile)
        }
        return jarFile
    }


}
