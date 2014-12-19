package com.gteam.model;

import java.io.Serializable;

/**
 * Created by nagarajan on 18/12/14.
 */
public class SourceCode implements Serializable {
    private static final long serialVersionUID = -1693029307078057575L;
    private String source;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
