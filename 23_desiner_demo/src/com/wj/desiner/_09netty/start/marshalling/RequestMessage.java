package com.wj.desiner._09netty.start.marshalling;

import java.io.Serializable;
import java.util.Arrays;

public class RequestMessage implements Serializable{
    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private byte[] attachment;

    public RequestMessage(int id, String name, byte[] attachment) {
        this.id = id;
        this.name = name;
        this.attachment = attachment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getAttachment() {
        return attachment;
    }

    public void setAttachment(byte[] attachment) {
        this.attachment = attachment;
    }

    @Override
    public String toString() {
        return "RequestMessage{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", attachment=" + Arrays.toString(attachment) +
                '}';
    }
}
