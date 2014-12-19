package com.gteam.ide;

/**
 * Created by nagarajan on 18/12/14.
 */
public interface LanguageProcessor {

    public CompilationResult compile(String code) throws Exception;

    public <T extends  CompilationResult> ExecutionResult run(T compilationResult) throws Exception;
}
