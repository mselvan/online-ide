package com.gteam.ide.java;

import com.gteam.ide.CompilationResult;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by nagarajan on 18/12/14.
 */
public class JavaCompliationResult extends CompilationResult {
    private static final long serialVersionUID = -8407019566427442754L;

    private List<ClassInfo> classInfos;

    private final ByteArrayOutputStream errorStream = new ByteArrayOutputStream();
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    public List<ClassInfo> getClassInfos() {
        return classInfos;
    }

    public void setClassInfos(List<ClassInfo> classInfos) {
        this.classInfos = classInfos;
    }

    public int getMainClassIndex() throws Exception {
        int index = -1;
        if (classInfos != null && classInfos.size() > 0) {
            CustomClassLoader classLoader = new CustomClassLoader(classInfos);
            for (int i = 0; i < classInfos.size(); i++) {
                final ClassInfo classInfo = classInfos.get(i);
                final Class<?> solution = classLoader.loadClass(classInfo.className);
                final Method[] declaredMethods = solution.getDeclaredMethods();
                for (Method declaredMethod : declaredMethods) {
                    if (declaredMethod.getName().equals("main")) {
                        index = i;
                    }
                }
            }
        }
        return index;
    }

    public ByteArrayOutputStream errorStream() {
        return errorStream;
    }

    public ByteArrayOutputStream outputStream() {
        return outputStream;
    }
}
