package com.sct.service.sucurity.support.password.type.sct;

import com.sct.service.sucurity.support.password.type.AbstractPasswordEncoder;
import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.jcajce.provider.digest.SHA3;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.codec.Utf8;
import org.springframework.security.crypto.util.EncodingUtils;

import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.UUID;

public class SctPasswordEncoder extends AbstractPasswordEncoder {
    private static final Logger logger = LoggerFactory.getLogger(SctPasswordEncoder.class);

    private final MessageDigest digester;

    private String privateSecrt = null;
    private byte[] privateSecrtBytes = null;

    public SctPasswordEncoder() {
        super(32);
        MessageDigest digester;
        try {
            digester = MessageDigest.getInstance("SHA3-256");
        } catch (NoSuchAlgorithmException e) {
            //try {
            //	digester = MessageDigest.getInstance("SHA3-256", "BC");
            //}
            //catch (NoSuchAlgorithmException | NoSuchProviderException e1) {
            //	throw new RuntimeException(e1);
            //}
            digester = new SHA3.Digest256();
        }
        this.digester = digester;

        // Try unlimited cryptographic policy first
        try {
            this.digester.digest(generateSalt());
        } catch (Exception e) {
            //noinspection ConstantConditions
            if (e instanceof InvalidKeyException) {
                throw new RuntimeException(
                        "You need enable unlimited cryptographic policy in Oracle JDK.");
            }
        }
    }

    public static void main(String[] args) {
        SctPasswordEncoder hiosPasswordEncoder = new SctPasswordEncoder();
        boolean ok = hiosPasswordEncoder.matches("Aa123456", "ZGY2MTk2ZWZkZjhlNjk2MDYxZTZlMmU0ZTE1ZWIyNjRjZWZhMjQ2NzJmZGI3MzFlMTAyZDRjNjhkYjgyM2EzMA==");
        System.out.println(ok);
        System.out.println(UUID.randomUUID().toString());
        String password = hiosPasswordEncoder.encode("abc123xyz");
        System.out.println(password);
        System.out.println(hiosPasswordEncoder.matches("abc123xyz", password));
    }

    /**
     * 初始化固定私有的盐,如果有固定私有盐的话
     *
     * @param privateSecrt 私有盐,如果不为空,则在加密时使用,否则使用随机生成的
     */
    public void initSalt(String privateSecrt) {
        this.privateSecrt = privateSecrt;
        if (StringUtils.isNotEmpty(privateSecrt)) {
            this.privateSecrtBytes = this.privateSecrt.getBytes();
        }
    }

    @Override
    public String encode(CharSequence rawPassword) {
        byte[] salt = null;
        if (privateSecrtBytes != null) {
            salt = privateSecrtBytes;
        } else {
            salt = generateSalt();
        }
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
                salt2 = this.digester.digest(concatenate(salt, Utf8.encode(inputs[0])));
                rawPassword = inputs[1];
            }
        }

        byte[] digested = this.digester.digest(concatenate(salt2 != null ? salt2 : salt,
                Utf8.encode(rawPassword)));

        return concatenate(salt, digested);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        try {
            byte[] digested = Base64.getDecoder().decode(encodedPassword);
            byte[] salt = EncodingUtils.subArray(digested, 0, getSaltLength());
            return matches(digested, encode(rawPassword, salt));
        } catch (Exception e) {
            logger.error(
                    "Encoded password is invalid: " + String.valueOf(encodedPassword), e);
            return false;
        }
    }

    protected int getSaltLength() {
        if (privateSecrtBytes != null) {
            return privateSecrtBytes.length;
        } else {
            return super.getSaltLength();
        }
    }
}
