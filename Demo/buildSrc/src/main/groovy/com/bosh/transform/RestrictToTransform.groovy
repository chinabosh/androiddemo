package com.bosh.transform

import com.alibaba.android.arouter.register.core.RegisterTransform
import com.alibaba.android.arouter.register.utils.ScanSetting
import com.android.build.api.transform.Context
import com.android.build.api.transform.Format
import com.android.build.api.transform.JarInput
import com.android.build.api.transform.QualifiedContent
import com.android.build.api.transform.Status
import com.android.build.api.transform.Transform
import com.android.build.api.transform.TransformException
import com.android.build.api.transform.TransformInput
import com.android.build.api.transform.TransformInvocation
import com.android.build.api.transform.TransformOutputProvider
import com.android.build.gradle.internal.pipeline.TransformManager
import com.android.ddmlib.Log
import com.android.utils.FileUtils
import com.bosh.utils.Logger
import javassist.ClassPool
import javassist.CtClass
import javassist.NotFoundException
import javassist.bytecode.AnnotationsAttribute
import javassist.bytecode.ClassFile
import javassist.bytecode.ConstPool
import javassist.bytecode.annotation.Annotation
import javassist.bytecode.annotation.ClassMemberValue
import javassist.bytecode.annotation.EnumMemberValue
import org.apache.commons.codec.digest.DigestUtils
import org.gradle.api.Project
import org.objectweb.asm.AnnotationVisitor
import org.objectweb.asm.ClassReader
import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.ClassWriter
import org.objectweb.asm.Opcodes

import java.util.jar.JarEntry
import java.util.jar.JarFile

class RestrictToTransform extends Transform {

    static ArrayList<String> restrictToList = new ArrayList<>()
    static ArrayList<String> restrictPathList = new ArrayList<>()
    static mProject

    RestrictToTransform(Project project) {
        super()
        mProject = project
    }

    @Override
    String getName() {
        return 'RestrictToTransform'
    }

    @Override
    Set<QualifiedContent.ContentType> getInputTypes() {
        return TransformManager.CONTENT_CLASS
    }

    @Override
    Set<? super QualifiedContent.Scope> getScopes() {
        return TransformManager.SCOPE_FULL_PROJECT
    }

    @Override
    boolean isIncremental() {
        return false
    }

    @Override
    void transform(TransformInvocation transformInvocation) throws TransformException, InterruptedException, IOException {
//        super.transform(transformInvocation)
        def inputs = transformInvocation.inputs
        def outputProvider = transformInvocation.outputProvider
        def isIncremental = transformInvocation.incremental
        if (!isIncremental) {
            outputProvider.deleteAll()
        }
        for (TransformInput input : inputs) {
            for (JarInput jarInput : input.jarInputs) {
                String destName = jarInput.name
                def hexName = DigestUtils.md5Hex(jarInput.file.absolutePath)
                if (destName.endsWith(".jar")) {
                    destName = destName.substring(0, destName.length() - 4)
                }
                File dest = outputProvider.getContentLocation(destName + "_" + hexName, jarInput.contentTypes, jarInput.scopes, Format.JAR)
//                scanClass(jarInput.file)
                def jarFile = jarInput.file
                if (shouldProcessPreDexJar(jarFile.absolutePath)) {
                    if (jarFile) {
                        def file = new JarFile(jarFile)
                        Enumeration enumeration = file.entries()
                        while (enumeration.hasMoreElements()) {
                            JarEntry jarEntry = (JarEntry) enumeration.nextElement()
                            String entryName = jarEntry.getName()
                            if (entryName.endsWith(".class")) {
                                InputStream inputStream = file.getInputStream(jarEntry)
                                scanClass(inputStream, jarFile.absolutePath)
                                inputStream.close()
                            }
                        }
                        file.close()
                    }
                }
//                restrictPathList.each {path ->
//                    GenerateRestrictTo.appendClassPath(path)
//                }
//                restrictToList.each { name ->
//                    GenerateRestrictTo.generate(name)
//                }
                Logger.w("jarFile from:" + jarFile.absolutePath + ",to:" + dest.absolutePath)
                FileUtils.copyFile(jarFile, dest)
            }
            restrictPathList.clear()
            restrictToList.clear()
            input.directoryInputs.each { directoryInput ->
                File dest = outputProvider.getContentLocation(directoryInput.name, directoryInput.contentTypes, directoryInput.scopes, Format.DIRECTORY)
//                directoryInput.file.eachFileRecurse { file ->
//                    if (file.isFile() && file.absolutePath.contains('bosh')) {
//                        scanClass(file)
//                    }
//                }
//                restrictPathList.each {path ->
//                    GenerateRestrictTo.appendClassPath(path)
//                }
//                restrictToList.each { name ->
//                    GenerateRestrictTo.generate(name)
//                }
                GenerateRestrictTo.generate(directoryInput.file, mProject)
                Logger.w("from :" + directoryInput.file.absolutePath + " ,dest:" + dest.absolutePath)
                FileUtils.copyDirectory(directoryInput.file, dest)
            }
        }


    }

    static boolean shouldProcessPreDexJar(String path) {
        return !path.contains("com.android.support") && !path.contains("/android/m2repository")
    }

    static void scanClass(File file) {
        scanClass(new FileInputStream(file), file.absolutePath)
    }

    static void scanClass(InputStream inputStream, String path) {
        ClassReader cr = new ClassReader(inputStream)
        ClassWriter cw = new ClassWriter(cr, 0)
        ScanClassVisitor cv = new ScanClassVisitor(Opcodes.ASM5, cw, path)
        cr.accept(cv, ClassReader.EXPAND_FRAMES)
        inputStream.close()
    }

    static class ScanClassVisitor extends ClassVisitor {

        String classname
        String mPath

        ScanClassVisitor(int api, ClassVisitor cv, String path) {
            super(api, cv)
            mPath = path
        }

        void visit(int version, int access, String name, String signature,
                   String superName, String[] interfaces) {
            super.visit(version, access, name, signature, superName, interfaces)
            classname = name
            if (classname.contains("bosh") && !classname.contains("R\$") && !classname.contains("R2\$")) {
                restrictToList.add(name)
                restrictPathList.add(mPath)
            }
        }

        @Override
        AnnotationVisitor visitAnnotation(String desc, boolean visible) {
            if (desc.contains("com/china/bosh/mylibrary/annotation/Open") || desc.contains("androidx/annotation/RestrictTo")) {
                if (restrictToList.contains(classname)) {
                    restrictToList.remove(classname)
                }
            }
            return super.visitAnnotation(desc, visible)
        }
    }

}
