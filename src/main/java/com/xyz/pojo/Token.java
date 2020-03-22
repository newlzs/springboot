package com.xyz.pojo;

/**
* @author lzs
* @Date 2020/3/21
*/

public class Token {
    private long userId;
    private int client;
    private String ip;
    private String token;
    private long createdAt;
    private long updatedAt;
    private long expiresAt;

    public long getUpdateAt() {
        return updatedAt;
    }

    public void setUpdateAt(long updatedAt) {
        this.updatedAt = updatedAt;
    }



    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getClient() {
        return client;
    }

    public void setClient(int client) {
        this.client = client;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public long getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(long expiresAt) {
        this.expiresAt = expiresAt;
    }
}
