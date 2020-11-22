package com.sct.service.core.web.rsa.cache;

public class RasKeyCacheInfo implements java.io.Serializable {
    private String publicKey;
    private String privateKey;
    //<=0 永不超时
    private long expireDate;

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public long getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(long expireDate) {
        this.expireDate = expireDate;
    }
}
