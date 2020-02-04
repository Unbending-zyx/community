package com.student.community.modle;


public class User {
    private int id;
    private String gitAccountId;
    private String username;
    private String password;
    private String gitName;
    private String gitBio;
    private String accountName;
    private String token;
    private Long createTime;
    private Long updateTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGitAccountId() {
        return gitAccountId;
    }

    public void setGitAccountId(String gitAccountId) {
        this.gitAccountId = gitAccountId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGitName() {
        return gitName;
    }

    public void setGitName(String gitName) {
        this.gitName = gitName;
    }

    public String getGitBio() {
        return gitBio;
    }

    public void setGitBio(String gitBio) {
        this.gitBio = gitBio;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }


    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
