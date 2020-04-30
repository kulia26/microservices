package com.example.client;

import java.io.Serializable;

public class Signed<T> implements Serializable {
    private final T data;
    private final String instanceId;

    public Signed(T data, String instanceId) {
        this.data = data;
        this.instanceId = instanceId;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public T getData() {
        return data;
    }
}