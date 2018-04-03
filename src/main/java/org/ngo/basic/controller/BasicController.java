package org.ngo.basic.controller;

import org.ngo.basic.model.ResultDTO;

public abstract class BasicController {

    protected ResultDTO result;

    public ResultDTO getResult() {
        return result;
    }

    public void setResult(ResultDTO result) {
        this.result = result;
    }
}
