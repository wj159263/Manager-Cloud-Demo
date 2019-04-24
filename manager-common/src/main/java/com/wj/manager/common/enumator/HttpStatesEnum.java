package com.wj.manager.common.enumator;

import lombok.Data;

public enum HttpStatesEnum {
    BAD_GATEWAY(502,"从上有接收到无效响应")
    ;

    private int status;
    private String content;

    private HttpStatesEnum(int status, String content){
        this.status = status;
        this.content = content;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
