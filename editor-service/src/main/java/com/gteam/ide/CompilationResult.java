package com.gteam.ide;

import java.io.Serializable;

/**
 * Created by nagarajan on 18/12/14.
 */
public abstract class CompilationResult implements Serializable {
    private static final long serialVersionUID = -8407019566427442754L;
    private String error;
    private String output;
    private String source;

    public boolean hasErrors() {
        final String errorString = getError();
        if(errorString != null && errorString.trim().length() > 0) {
            return true;
        }
        return false;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
