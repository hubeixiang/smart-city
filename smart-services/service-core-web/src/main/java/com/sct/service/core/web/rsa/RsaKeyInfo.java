package com.sct.service.core.web.rsa;

import org.apache.commons.codec.binary.Base64;

import java.security.Key;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

public class RsaKeyInfo {
    private RSAPublicKey publicKey;
    private RSAPrivateKey privateKey;

    public RSAPublicKey getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(RSAPublicKey publicKey) {
        this.publicKey = publicKey;
    }

    public RSAPrivateKey getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(RSAPrivateKey privateKey) {
        this.privateKey = privateKey;
    }

    public String getPublicKeyDecode() {
        Key key = (Key) publicKey;
        return Base64.encodeBase64String(key.getEncoded());
    }

    public String getPrivateKeyDecode() {
        Key key = (Key) privateKey;
        return Base64.encodeBase64String(key.getEncoded());
    }
}
