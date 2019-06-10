package com.wj.desiner._09netty.start.marshalling;

import java.io.Serializable;

public class ResponseMessage implements Serializable{
    private static final long serialVersionUID = 1L;
    private long id;
    private String name;

    public ResponseMessage(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ResponseMessage{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
