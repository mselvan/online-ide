package com.gteam.ide;

import java.io.Serializable;

/**
 * Created by nagarajan on 18/12/14.
 */
public class ExecutionResult implements Serializable {
    private static final long serialVersionUID = 5472558457331679653L;
    private CompilationResult compilationResult;
    private boolean errors = false;
    private String errorString;
    private String result;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public CompilationResult getCompilationResult() {
        return compilationResult;
    }

    public void setCompilationResult(CompilationResult compilationResult) {
        this.compilationResult = compilationResult;
    }

    public boolean isErrors() {
        return errors;
    }

    public void setErrors(boolean errors) {
        this.errors = errors;
    }

    public String getErrorString() {
        if(errorString == null && isErrors()) {
            return getCompilationResult().getError();
        }
        return errorString;
    }

    public void setErrorString(String errorString) {
        this.errorString = errorString;
    }
}
