package org.ngo.basic.controller;

import org.ngo.basic.model.ResultVO;

public abstract class BasicController {

    protected ResultVO result;

    public ResultVO getResult() {
        return result;
    }

    public void setResult(ResultVO result) {
        this.result = result;
    }
}
