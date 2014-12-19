package com.gteam.ide;

import com.gteam.ide.java.ClassInfo;
import com.gteam.ide.java.CustomClassLoader;
import com.gteam.ide.java.JavaCompliationResult;
import com.gteam.ide.java.Javac;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * Created by nagarajan on 18/12/14.
 */
public class JavaProcessor implements LanguageProcessor {
    public CompilationResult compile(String source) throws Exception {
        final ArrayList<String> srcCodes = new ArrayList<String>();
        srcCodes.add(source);
        final CompilationResult compilationResult = Javac.compile(srcCodes);
        compilationResult.setSource(source);
        return compilationResult;
    }

    @Override
    public <T extends CompilationResult> ExecutionResult run(T compilationResult) throws Exception {
        if(compilationResult instanceof JavaCompliationResult){
            return execute((JavaCompliationResult) compilationResult);
        }
        throw new Exception("Invalid Byte Code");
    }

    public ExecutionResult execute(JavaCompliationResult compilationResult) throws Exception {

        ExecutionResult result = new ExecutionResult();
        result.setCompilationResult(compilationResult);
        result.setErrors(compilationResult.hasErrors());
        if (compilationResult.hasErrors() == Boolean.FALSE) {
            int index = compilationResult.getMainClassIndex();
            final ClassInfo classInfo = compilationResult.getClassInfos().get(index);
            CustomClassLoader classLoader = new CustomClassLoader(compilationResult.getClassInfos());
            final Class<?> solution = classLoader.loadClass(classInfo.className);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PrintStream stream = new PrintStream(outputStream);
            System.setOut(stream);
            final Method mainMethod = solution.getDeclaredMethod("main", new Class[]{String[].class});
            if (mainMethod != null) {
                try {
                    mainMethod.invoke(null, new Object[]{null});
                } catch (Exception e) {
                    result.setErrors(true);
                    ByteArrayOutputStream runtimeErrorStream = new ByteArrayOutputStream();
                    e.printStackTrace(new PrintStream(runtimeErrorStream));
                    result.setErrorString(runtimeErrorStream.toString());
                }
            }
            result.setResult(outputStream.toString());
        }
        return result;
    }
}
