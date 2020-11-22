package com.sct.service.sucurity.support.password.type.sct;

import com.sct.service.sucurity.support.password.type.AbstractPasswordEncoder;
import com.sct.service.sucurity.support.password.type.PasswordEncryptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.security.crypto.codec.Utf8;
import org.springframework.security.crypto.encrypt.BytesEncryptor;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.util.EncodingUtils;

import java.security.InvalidKeyException;
import java.util.Base64;

/**
 * 可逆的加密方式
 */
public class SctFixedPasswordEncoder extends AbstractPasswordEncoder implements PasswordEncryptor {

    private static Logger logger = LoggerFactory.getLogger(SctFixedPasswordEncoder.class);

    //固定加密的盐
    private final CharSequence password;

    public SctFixedPasswordEncoder(CharSequence password) {
        super(32);
        if (password == null || password.length() == 0) {
            throw new IllegalArgumentException("A password must not be null.");
        }
        this.password = password;
    }

    @Override
    public String decode(String encodedPassword) {
        byte[] digested;
        byte[] salt;
        // Salt again with user id if exists, to prevent password clone by dba.
        if (encodedPassword.contains("::")) {
            String[] inputs = encodedPassword.split("::", 2);
            digested = Base64.getDecoder().decode(inputs[1]);
            salt = EncodingUtils.subArray(digested, 0, getSaltLength());
            salt = concatenate(salt, Utf8.encode(inputs[0]));
            if (salt.length % 2 != 0) {
                salt = concatenate(salt, new byte[]{0});
            }
        } else {
            digested = Base64.getDecoder().decode(encodedPassword);
            salt = EncodingUtils.subArray(digested, 0, getSaltLength());
        }
        digested = EncodingUtils.subArray(digested, getSaltLength(), digested.length);

        BytesEncryptor encryptor = getEncryptor(salt);
        byte[] rawPassword = encryptor.decrypt(digested);

        return Utf8.decode(rawPassword);
    }

    @Override
    public String encode(CharSequence rawPassword) {
        byte[] salt = generateSalt();
        byte[] digested = encode(rawPassword, salt);
        return Base64.getEncoder().encodeToString(digested);
    }

    private byte[] encode(CharSequence rawPassword, byte[] salt) {
        byte[] salt2 = null;
        if (rawPassword instanceof String) {
            String input = (String) rawPassword;
            // Salt again with user id if exists, to prevent password clone by dba.
            if (input.contains("::")) {
                String[] inputs = input.split("::", 2);
                salt2 = concatenate(salt, Utf8.encode(inputs[0]));
                if (salt2.length % 2 != 0) {
                    salt2 = concatenate(salt2, new byte[]{0});
                }
                rawPassword = inputs[1];
            }
        }

        BytesEncryptor encryptor = getEncryptor(salt2 != null ? salt2 : salt);
        byte[] digested = encryptor.encrypt(Utf8.encode(rawPassword));

        return concatenate(salt, digested);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        if (rawPassword instanceof String) {
            String input = (String) rawPassword;
            if (input.contains("::")) {
                String[] inputs = input.split("::", 2);
                rawPassword = inputs[1];
                encodedPassword = String.format("%s::%s", inputs[0], encodedPassword);
            }
        }

        String decodedPassword;
        try {
            decodedPassword = decode(encodedPassword);
        } catch (Exception e) {
            logger.error("Fail to decode.", e);
            return false;
        }
        rawPassword = rawPassword instanceof String ?
                (String) rawPassword :
                Utf8.decode(Utf8.encode(rawPassword));

        return rawPassword.equals(decodedPassword);
    }

    private BytesEncryptor getEncryptor(byte[] salt) {
        try {
            return Encryptors.stronger(this.password, new String(Hex.encode(salt)));
        } catch (IllegalArgumentException e) {
            if (e.getCause() instanceof InvalidKeyException) {
                throw new IllegalStateException(
                        "Require to Installing JCE Unlimited Strength Jurisdiction policy files.",
                        e);
            }
            throw e;
        }
    }
}
