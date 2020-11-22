package com.sct.service.sucurity.support.password.type;

public interface PasswordEncryptor {
    String decode(String encodedPassword);
}
