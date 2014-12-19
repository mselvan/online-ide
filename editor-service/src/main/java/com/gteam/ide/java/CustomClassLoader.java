package com.gteam.ide.java;

import java.util.List;

/**
 * Created by nagarajan on 18/12/14.
 */
public class CustomClassLoader extends ClassLoader {

    public CustomClassLoader(List<ClassInfo> classInfos) {
        for (ClassInfo classInfo : classInfos) {
            this.defineClass(classInfo.className, classInfo.bytes, 0, classInfo.bytes.length);
        }
    }
}
