package com.gteam;

import com.gteam.ide.JavaProcessor;
import com.gteam.ide.CompilationResult;
import com.gteam.ide.ExecutionResult;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.Assert.*;

public class JIdeProcessorTest {

    private JavaProcessor processor;
    private CompilationResult compilationResult;

    @Before
    public void setUp() throws Exception {
        processor = new JavaProcessor();
    }

    @After
    public void tearDown() throws Exception {
        processor = null;
    }

    @Test
    public void testCompile_positive() throws Exception {
        compilationResult = processor.compile(getNonErrorCode());
        assertNotNull(compilationResult);
        assertFalse(compilationResult.hasErrors());
    }

    @Test
    public void testCompile_negative() throws Exception {
        compilationResult = processor.compile(getErrorCode());
        assertNotNull(compilationResult);
        assertTrue(compilationResult.hasErrors());
    }

    @Test
    public void testRun() throws Exception {
        compilationResult = processor.compile(getNonErrorCode());
        ExecutionResult executionResult = processor.run(compilationResult);
        assertEquals("Hello World!\n", executionResult.getResult());
    }

    private static String getNonErrorCode() {
        StringWriter writer = new StringWriter();
        PrintWriter out = new PrintWriter(writer);
        out.println("public class Solution {");
        out.println("  public static void main(String args[]) {");
        out.println("    System.out.println(\"Hello World!\");");
        out.println("  }");
        out.println("}");
        out.close();
        return writer.toString();
    }

    private static String getErrorCode() {
        StringWriter writer = new StringWriter();
        PrintWriter out = new PrintWriter(writer);
        out.println("public class Solution {");
        out.println("  public static void main(String args[]) {");
        out.println("    System.out.println(\"Hello World!\")");
        out.println("  }");
        out.println("}");
        out.close();
        return writer.toString();
    }

}