package com.sct.service.core.web.captcha;

import java.awt.image.BufferedImage;

public class CaptchaCodeInfo {
    private BufferedImage bufferedImage;
    private String key;
    private String code;

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }

    public void setBufferedImage(BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
